package com.feng.common;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 18-12-26.
 */

public class BaseApp extends Application {
    public static BaseApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Logger.init("Keep").setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
        Logger.i("AppCreat -> " + getClass().getSimpleName());
    }
}
