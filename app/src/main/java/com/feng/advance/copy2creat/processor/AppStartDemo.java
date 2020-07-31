package com.feng.advance.copy2creat.processor;

import com.feng.rounte_annotation.AppInitSort;
import com.feng.rounte_annotation.IAppInit;
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
            for (AppInitSort appInif : mAppSort) {
                IAppInit iAppInit = (IAppInit) Class.forName(appInif.clazz).newInstance();
                iAppInit.init();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("err = " + e);
        }
    }
}
