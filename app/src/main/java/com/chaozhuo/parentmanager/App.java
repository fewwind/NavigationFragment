package com.chaozhuo.parentmanager;

import android.content.Context;

import com.chaozhuo.common.BaseApp;

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
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
