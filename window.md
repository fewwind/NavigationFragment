# 关于window
#### WindowManager 起源
- `WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE)` ContextImpl是context是实现类，此类有多个静态方法 createSystemContext、createAppContext、createActivityContext
创建不同级别的Context，殊途同归只是参数不同，找到
```java
    @Override
    public Object getSystemService(String name) {
                                      return SystemServiceRegistry.getSystemService(this, name);
                                  }
                                  ```
                                  可以看到是 SystemServiceRegistry 类的方法，`registerService(Context.WINDOW_SERVICE, WindowManager.class,
                                                            new CachedServiceFetcher<WindowManager>() {
                                                        @Override
                                                        public WindowManager createService(ContextImpl ctx) {
                                                            return new WindowManagerImpl(ctx);
                                                        }});` 
 这是一段静态代码块，初始化的时候创建了很多服务。window的实现类WindowManagerImpl，`
 @Override
 public void addView(@NonNull View view, @NonNull ViewGroup.LayoutParams params) { 
 mGlobal.addView(view, params, mContext.getDisplay(), mParentWindow); }` mGlobal是个全局单例的WindowManagerGlobal对象，mGlobal的addView方法很长，简单说
 new了ViewRootImpl对象，然后调用`root.setView(view, wparams, panelParentView);`通过`mWindowSession.addToDisplay`方法，mWindowSession由IWindowManager用过openSession方法返回，
 是个IWindowSession接口，aidl的代理对象,Session是其服务端的实现类，最终通过wms的addWindow添加到显示屏。
                                                                                                                       


![主页](https://raw.githubusercontent.com/fewwind/img_folder/master/home_frag.png)

