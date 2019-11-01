package com.chaozhuo.parentmanager;

import android.content.Context;

import com.chaozhuo.common.BaseApp;
import com.chaozhuo.login.LoginApp;
import com.chaozhuo.route_api.RouteDemo;

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
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApp login = new LoginApp();
        login.onCreate();
    }
}
