package com.chaozhuo.parentmanager.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import com.chaozhuo.common.BaseApp;
import com.chaozhuo.parentmanager.BuildConfig;
import com.chaozhuo.parentmanager.bean.AppInfoBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ryanhuen on 18-10-10.
 */

public class PackageUtils {
    public static final String TAG = PackageUtils.class.getName();
    private static final Object mLock = new Object();

    public static List<ResolveInfo> resolveMainIntentActivities(Context context, String packageName) {
        synchronized (mLock) {
            final Intent mainIntent = new Intent(Intent.ACTION_MAIN);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            if (!TextUtils.isEmpty(packageName)) {
                mainIntent.setPackage(packageName);
                return getQueryIntentActivities(context, mainIntent);
            }

            final List<ResolveInfo> list = getQueryIntentActivities(context, mainIntent);

            return list;
        }

    }

    public static List<ResolveInfo> getQueryIntentActivities(Context context, Intent intent) {
        // User is not in STATE_RUNNING_UNLOCKED, STATE_RUNNING_UNLOCKING also remove all, view UserState class
        final int flag = PackageManager.MATCH_DIRECT_BOOT_UNAWARE | PackageManager.MATCH_DIRECT_BOOT_AWARE;
        return context.getPackageManager().queryIntentActivities(intent, flag);
    }

    public static List<AppInfoBean> getAllInstalledApps(Context context) {
        final List<AppInfoBean> list = new ArrayList<>();

        final PackageManager pm = context.getPackageManager();
//        final List<ResolveInfo> apps = resolveMainIntentActivities(context, null);
        final List<ResolveInfo> apps = resolveMainIntentActivities(context, null);
        for (final ResolveInfo info : apps) {
            // 此处只存储图片即可，后续内容会换成新的基于数据库中内容生成的SecondItemBean
            Drawable dr = loadIconForDensity(context, info.activityInfo);
            final AppInfoBean item = new AppInfoBean();
            item.setPkgName(info.activityInfo.packageName);
            item.setActivityName(info.activityInfo.name);
            item.setTitle(info.loadLabel(pm).toString());
            item.setImageDrawable(dr);

            list.add(item);
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "getAllInstalledApps: " + list.size() + " apps returned.");
        }
        return list;
    }

    public static AppInfoBean getAppInfoByPkg(String pkg) {

        final PackageManager pm = BaseApp.app.getPackageManager();
        final List<ResolveInfo> apps = resolveMainIntentActivities(BaseApp.app, pkg);
        if (apps == null || apps.isEmpty()) return null;
        // 此处只存储图片即可，后续内容会换成新的基于数据库中内容生成的SecondItemBean
        ResolveInfo info = apps.get(0);
        Drawable dr = loadIconForDensity(BaseApp.app, info.activityInfo);
        final AppInfoBean item = new AppInfoBean();
        item.setPkgName(info.activityInfo.packageName);
        item.setActivityName(info.activityInfo.name);
        item.setTitle(info.loadLabel(pm).toString());
        item.setImageDrawable(dr);
        return item;
    }

    public static Map<String, AppInfoBean> getAllBeanMap(Context context) {
        final Map<String, AppInfoBean> map = new HashMap<>();

        final PackageManager pm = context.getPackageManager();
//        final List<ResolveInfo> apps = resolveMainIntentActivities(context, null);
        final List<ResolveInfo> apps = resolveMainIntentActivities(context, null);
        for (final ResolveInfo info : apps) {
            // 此处只存储图片即可，后续内容会换成新的基于数据库中内容生成的SecondItemBean
            Drawable dr = loadIconForDensity(context, info.activityInfo);
            final AppInfoBean item = new AppInfoBean();
            item.setPkgName(info.activityInfo.packageName);
            item.setActivityName(info.activityInfo.name);
            item.setTitle(info.loadLabel(pm).toString());
            item.setImageDrawable(dr);

            String key = info.activityInfo.packageName + "_" + info.activityInfo.name;
            map.put(key, item);
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "getAllInstalledApps: " + map.size() + " apps returned.");
        }
        return map;
    }

    public static Drawable loadIconForDensity(Context context, ActivityInfo ai) {
        PackageManager pm = context.getPackageManager();
        int iconId = 0;
        if (ai.icon != 0) {
            iconId = ai.icon;
        } else if (ai instanceof android.content.pm.ComponentInfo) {
            android.content.pm.ApplicationInfo applicationInfo = ai.applicationInfo;
            iconId = applicationInfo.icon;
        }
        if (iconId == 0) {
            return ai.loadIcon(pm);
        }
        try {
            Resources resources = pm.getResourcesForApplication(ai.packageName);
            int densityDpi = resources.getDisplayMetrics().densityDpi * 2;
            if (densityDpi > 480) {
                densityDpi = 480;
            }
            return resources.getDrawableForDensity(iconId, densityDpi);
        } catch (Exception e) {
        }
        return ai.loadIcon(pm);
    }


    private void getAppList(Context context) {
        PackageManager pm = context.getPackageManager();
        // Return a List of all packages that are installed on the device.
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            // 判断系统/非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) // 非系统应用
            {
                System.out.println("MainActivity.getAppList, packageInfo=" + packageInfo.packageName);
            } else {
                // 系统应用
            }
        }
    }
}
