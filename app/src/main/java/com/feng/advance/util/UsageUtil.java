package com.feng.advance.util;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Base64;

import com.feng.common.BaseApp;
import com.feng.advance.R;
import com.feng.advance.bean.AppInfoBean;
import com.feng.advance.bean.AppUsageBean;
import com.feng.advance.bean.AppUsageTotalBean;
import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fewwind on 18-12-18.
 */

public class UsageUtil {

    private static final String KEY_BOOT_TIME = "key_boot_time";

    public static void updateUsage() {
        Observable<AppUsageTotalBean> usageListToday = getUsageList(UsageStatsManager.INTERVAL_DAILY, getTimesMorning(), System.currentTimeMillis(), true);
        Observable<AppUsageTotalBean> usageListWeek = getUsageList(UsageStatsManager.INTERVAL_WEEKLY, System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS, System.currentTimeMillis(), true);
        Observable.concat(usageListToday, usageListWeek).observeOn(Schedulers.io()).subscribe(new Consumer<AppUsageTotalBean>() {
            @Override
            public void accept(AppUsageTotalBean appUsageTotalBean) throws Exception {
            }
        });
    }

    public static Observable<AppUsageTotalBean> getUsageList(final int type, final long start, final long end, final boolean isNeedIcon) {
        final long[] maxTime = {0};
        UsageStatsManager mUsmManager = (UsageStatsManager) BaseApp.app.getSystemService(Context.USAGE_STATS_SERVICE);
        Observable<AppUsageTotalBean> observable = Observable.fromIterable(mUsmManager.queryUsageStats(type, start, end)).filter(new Predicate<UsageStats>() {
            @Override
            public boolean test(UsageStats usageStats) throws Exception {
                return !TextUtils.equals(usageStats.getPackageName(), BaseApp.app.getPackageName()) && usageStats.getTotalTimeInForeground() > DateUtils.MINUTE_IN_MILLIS;
            }
        }).sorted(new Comparator<UsageStats>() {
            @Override
            public int compare(UsageStats o1, UsageStats o2) {
                return (int) (o2.getTotalTimeInForeground() - o1.getTotalTimeInForeground());
            }
        }).distinct(new Function<UsageStats, String>() {
            @Override
            public String apply(UsageStats usageStats) throws Exception {
                return usageStats.getPackageName();
            }
        }).map(new Function<UsageStats, AppUsageBean>() {
            @Override
            public AppUsageBean apply(UsageStats usageStats) throws Exception {
                if (maxTime[0] == 0)
                    maxTime[0] = usageStats.getTotalTimeInForeground();
                AppUsageBean bean = new AppUsageBean();
                AppInfoBean appInfoByPkg = PackageUtils.getAppInfoByPkg(usageStats.getPackageName());
                if (appInfoByPkg == null) return bean;
//                Logger.v(appInfoByPkg.title + " = usage = " + usageStats.getTotalTimeInForeground());
                bean.drawable = appInfoByPkg.imageDrawable;
                bean.name = appInfoByPkg.title;
                bean.pkg = appInfoByPkg.pkgName;
                if (isNeedIcon) bean.icon = drawable2Base64(appInfoByPkg.imageDrawable);
                long timeInForeground = usageStats.getTotalTimeInForeground();
                bean.time = formatDateRange(timeInForeground);
                bean.progress = Math.max(5, (int) (usageStats.getTotalTimeInForeground() * 100.0f / maxTime[0]));
                return bean;
            }
        }).filter(new Predicate<AppUsageBean>() {
            @Override
            public boolean test(AppUsageBean bean) throws Exception {
                return !TextUtils.isEmpty(bean.pkg);
            }
        }).toList().toObservable().map(new Function<List<AppUsageBean>, AppUsageTotalBean>() {
            @Override
            public AppUsageTotalBean apply(List<AppUsageBean> appUsageBeans) throws Exception {
                AppUsageTotalBean totalBean = new AppUsageTotalBean();
                totalBean.usage = appUsageBeans;
                boolean isDay = type == UsageStatsManager.INTERVAL_DAILY;
                totalBean.priod = isDay ? AppUsageTotalBean.PRIOD_DAY : AppUsageTotalBean.PRIOD_WEEK;
                totalBean.totalTime = isDay ? getTotalUseTime() : getBootTimeWeek();
                maxTime[0] = 0;
                return totalBean;
            }
        }).subscribeOn(Schedulers.io());
        return observable;
    }
    
    public static void getRecentApp(){
        UsageStatsManager mUsmManager = (UsageStatsManager) BaseApp.app.getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats> usageStats = mUsmManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, System.currentTimeMillis() - DateUtils.HOUR_IN_MILLIS, System.currentTimeMillis());
        Observable.fromIterable(usageStats).sorted(new Comparator<UsageStats>() {
            @Override
            public int compare(UsageStats o1, UsageStats o2) {
                return (int) (o1.getLastTimeUsed() - o2.getLastTimeUsed());
            }
        }).takeLast(6).subscribe(new Consumer<UsageStats>() {

            @Override
            public void accept(UsageStats usageStats) throws Exception {
                String packageName = usageStats.getPackageName();
                AppInfoBean appInfoByPkg = PackageUtils.getAppInfoByPkg(packageName);
                if (appInfoByPkg !=null)Logger.w("最近使用 = "+appInfoByPkg.toString());
            }
        });
    }


    public static long getBootTimeWeek() {
        Set<String> time = PreferencesUtils.getStringSet(BaseApp.app, KEY_BOOT_TIME);
        long totalTime = 0;
        if (time != null) {

            Iterator<String> iterator = time.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (Long.valueOf(next) < (System.currentTimeMillis() - DateUtils.WEEK_IN_MILLIS)) {
                    PreferencesUtils.removeString(BaseApp.app, next);
                    iterator.remove();
                } else {
                    totalTime += PreferencesUtils.getLong(BaseApp.app, next);
                }
            }
        }
        totalTime += getBootUpTime();
        Set<String> newData = new HashSet<>(time);
        PreferencesUtils.putStringSet(BaseApp.app, KEY_BOOT_TIME, newData);
        Logger.w(totalTime + "" + newData.toString());
        return totalTime;
    }

    public static void saveBootTime() {
        Set<String> time = PreferencesUtils.getStringSet(BaseApp.app, KEY_BOOT_TIME);
        long bootUpTime = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        Set<String> newData = new HashSet<>(time);
        newData.add(String.valueOf(bootUpTime));
        PreferencesUtils.putStringSet(BaseApp.app, KEY_BOOT_TIME, newData);
        PreferencesUtils.putLong(BaseApp.app, String.valueOf(bootUpTime), SystemClock.uptimeMillis());
    }

    public static String drawable2Base64(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        //将byte[]转为base64
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static Bitmap base64ToBitmap(String base64) {

        byte[] decode = Base64.decode(base64, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static long getTodayTime() {
        return SystemClock.uptimeMillis();
    }

    public static long getBootUpTime() {
        return SystemClock.uptimeMillis();
    }

    public static long getTotalUseTime() {
        Set<String> time = PreferencesUtils.getStringSet(BaseApp.app, KEY_BOOT_TIME);
        long totalTime = 0;
        if (time != null) {
            Iterator<String> iterator = time.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (Long.valueOf(next) > getTimesMorning()) {
                    totalTime += PreferencesUtils.getLong(BaseApp.app, next);
                }
            }
        }
        totalTime += getTodayTime();
        return totalTime;
    }

    public static int getColor(int progress) {
        int value = progress / 24;
        int color = R.color.colorPrimary;
        if (value == 0) {
            color = R.color.progress_0;
        } else if (value == 1) {
            color = R.color.progress_1;
        } else if (value == 2) {
            color = R.color.progress_2;
        } else {
            color = R.color.progress_3;
        }
        return color;
    }

    public static long getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static String formatDateRange(long time) {
        StringBuilder sb = new StringBuilder();
        int hour = (int) (time / DateUtils.HOUR_IN_MILLIS);
        if (hour > 0) {
            sb.append(hour + BaseApp.app.getString(R.string.hour));
        }
        float min = (time % DateUtils.HOUR_IN_MILLIS) * 1.0f / DateUtils.MINUTE_IN_MILLIS;
        sb.append((int) Math.floor(min) + BaseApp.app.getString(R.string.minute));
        return sb.toString();
    }
}
