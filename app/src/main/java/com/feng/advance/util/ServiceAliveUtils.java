package com.feng.advance.util;

import android.app.ActivityManager;
import android.content.Context;

import com.feng.advance.App;

public class ServiceAliveUtils {

    public static boolean isServiceAlice() {
        boolean isServiceRunning = false;
        ActivityManager manager =
                (ActivityManager) App.app.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {
            return true;
        }
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("demo.lgm.com.keepalivedemo.service.DownloadService".equals(service.service.getClassName())) {
                isServiceRunning = true;
            }
        }
        return isServiceRunning;
    }
}