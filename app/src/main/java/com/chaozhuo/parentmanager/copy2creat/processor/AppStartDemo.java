package com.chaozhuo.parentmanager.copy2creat.processor;

import com.chaozhuo.rounte_annotation.AppInitSort;
import com.orhanobut.logger.Logger;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class AppStartDemo {

    public static ArrayList<AppInitSort> mAppSort = new ArrayList<>();


    public static void initAppStart() {
        try {
            Class<?> appStart = Class.forName("com.fewwind.learn.app_AppStart");
            Method method = appStart.getMethod("initModule", ArrayList.class);
            method.invoke(null, mAppSort);
            Logger.w("success"+mAppSort.size());
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("err = "+e);
        }
    }
}
