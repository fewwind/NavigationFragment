package com.feng.advance;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.alibaba.android.arouter.launcher.ARouter;
import com.feng.advance.copy2creat.processor.AppStartDemo;
import com.feng.common.BaseApp;
import com.feng.route_api.RouteDemo;
import com.orhanobut.logger.Logger;
import java.util.Set;

/**
 * Created by fewwind on 19-1-7.
 */

public class App extends BaseApp {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
//            InjectUtil.inject(this, getClassLoader());//加载插件Apk的类文件
//            HookHelper.hookAMS();
//            HookHelper.hookH();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RouteDemo.getInstance().init(this);
        if (BuildConfig.DEBUG) {
            //LeakCanary.install(this);
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
//        BaseApp login = new LoginApp();
//        login.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        AppStartDemo.initAppStart();
        findModuleApp();
    }

    //在模块中以接口名作为meta的value，key是实现类，然后反射
    private void findModuleApp() {
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            Set<String> strings = info.metaData.keySet();
            for (String key : strings) {
                if (info.metaData.getString(key).equals("IAppInit")) {
                    Logger.v("Key = " + key);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
