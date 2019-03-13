package com.chaozhuo.route_api;

import android.app.Application;
import android.content.Intent;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by fewwind on 19-2-20.
 */

public class RouteDemo {
    private RouteDemo() {
    }

    private static HashMap<String, Class> activityMap = new HashMap<>();
    private static Application mApp;
    private volatile static RouteDemo instance = null;
    private Intent mIntent;

    public static RouteDemo getInstance() {
        if (instance == null) {
            synchronized (RouteDemo.class) {
                if (instance == null) {
                    instance = new RouteDemo();
                }
            }
        }
        return instance;
    }

    public void init(Application application) {
        mApp = application;
        try {
            Class<?> aClass = Class.forName("com.fewwind.learn.AutoCreateModuleActivityMap_app");
            Method method = aClass.getMethod("initActivityMap", HashMap.class);
            method.invoke(null, activityMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RouteDemo build(String url) {
        Class aClass = activityMap.get(url);
        if (aClass != null) {
            mIntent = new Intent(mApp, aClass);
            return this;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public RouteDemo widthString(String key, String value) {
        mIntent.putExtra(key, value);
        return this;
    }

    public void start() {
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mApp.startActivity(mIntent);
    }
}
