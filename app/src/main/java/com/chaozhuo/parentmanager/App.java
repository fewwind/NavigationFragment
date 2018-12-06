package com.chaozhuo.parentmanager;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 18-12-4.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("ParentMG").setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
    }
}
