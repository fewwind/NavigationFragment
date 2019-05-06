   #### 获取系统服务 
   - context.getSystemService(Context.ACTIVITY_SERVICE);,context对应ContextImp，方法如下
   
    ```java
        @Override
        public Object getSystemService(String name) {
            return SystemServiceRegistry.getSystemService(this, name);
        }   
    ```
    `SystemServiceRegistry.class` 的静态代码块中已经注册了所有系统服务，注册的服务一般分两种
        
        ```java
        registerService(Context.ACTIVITY_SERVICE, ActivityManager.class,
                new CachedServiceFetcher<ActivityManager>() {
                    @Override
                    public ActivityManager createService(ContextImpl ctx) {
                        return new ActivityManager(ctx.getOuterContext(), ctx.mMainThread.getHandler());
                    }});

        registerService(Context.ALARM_SERVICE, AlarmManager.class,
                new CachedServiceFetcher<AlarmManager>() {
                    @Override
                    public AlarmManager createService(ContextImpl ctx) {
                        IBinder b = ServiceManager.getService(Context.ALARM_SERVICE);
                        IAlarmManager service = IAlarmManager.Stub.asInterface(b);
                        return new AlarmManager(service, ctx);
                    }});
         ```
         
  - 本质上一样，都是用Manager类封装从服务端获取的代理Ibinder对象，提供给客户端使用，目的是拓展功能和权限控制
        
  #### 系统服务是如何启动的
   - SystemServiceManager是SystemServer的工具类(成员变量)，负责启动各种系统服务(反射调用构造方法)，系统服务一般有两种,
       - 1是不需要提供给客户端，继承SystemService接口
       - 2是需要跨进程的IBinder对象，比如wms->`public class WindowManagerService extends IWindowManager.Stub`
   - Ams比较特殊，既要跨进程，也要继承SystemService接口，但是不能多继承，所以用Lifecycle包装了一层。
   先启动Lifecycle服务，服务内部启动ams.start();wms则是直接new 对象。
   
    ``` java
                public static final class Lifecycle extends SystemService {
                    private final ActivityManagerService mService;
        
                    public Lifecycle(Context context) {
                        super(context);
                        mService = new ActivityManagerService(context);
                    }
        
                    @Override
                    public void onStart() {
                        mService.start();
                    }
        
                    public ActivityManagerService getService() {
                        return mService;
                    }
                }
    ```
        ` SystemServiceManager.startService(ActivityManagerService.Lifecycle.class).getService() `
        
#### 启动后加入ServiceManager
   
   - 看到此类不仅有get()还有add(),在SystemServer系统进程启动的时候把需要跨进程的所有服务都add到了ServiceManager进程，
           比如wms的 `ServiceManager.addService(Context.WINDOW_SERVICE, wm);`ServiceManager就像桥梁，联通客户端和服务端,服务端把Binder的Stub实现类加入到ServiceManager
           ，客户端也通过ServiceManager获取系统服务的代理端。就像普通aidl中在服务端的Service中onBind（）方法返回服务端的Stub，客户端通过bingservice获取代理对象
           ```java
           public static void addService(String name, IBinder service) { // 第二个参数是服务端的binder
               try {
                   getIServiceManager().addService(name, service, false);
               } catch (RemoteException e) {
                   Log.e(TAG, "error in addService", e);
               }
           }
           ```
   Binder使用两个步骤，1是获取服务端IBinder对象，2是用IAlarmManager.Stub.asInterface(IBinder)得到代理对象
   #### 获取系统服务
   
   - 服务端的IBinder都是通过 ServiceManager.getService()获取的，源码如下，先是获取IServiceManager代理，通过binder获取系统binder，经过了两次IPC。
    ```java
        public static IBinder getService(String name) {
            try {
                IBinder service = sCache.get(name);
                if (service != null) {
                    return service;
                } else {
                    return getIServiceManager().getService(name);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "error in getService", e);
            }
            return null;
        }
    ```