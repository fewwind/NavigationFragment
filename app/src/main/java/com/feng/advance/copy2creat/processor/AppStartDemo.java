package com.feng.advance.copy2creat.processor;

import com.feng.rounte_annotation.AppInitSort;
import com.feng.rounte_annotation.IAppInit;
import com.orhanobut.logger.Logger;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class AppStartDemo {
    // 主module因为使用room引用了kapt注解处理器，导致自定义失效，login中是正常的
    public static ArrayList<AppInitSort> mAppSort = new ArrayList<>();


    /**
     * 注解处理器只能作用于module，生成的类请看 $login_AppStart
     */
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
