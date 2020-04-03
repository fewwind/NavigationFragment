package com.chaozhuo.parentmanager.util;

import android.text.format.DateUtils;

import com.chaozhuo.parentmanager.App;

import java.util.Calendar;

public enum TimeFormat {


    TODAY(0, 1) {
        @Override
        String getTime(long time) {
            return DateUtils.formatDateTime(App.app, time, DateUtils.FORMAT_SHOW_TIME);
        }
    }, YSETODAY(-1, 0) {
        @Override
        String getTime(long time) {
            return "昨天";
        }
    }, WEEK(-6, -1) {
        @Override
        String getTime(long time) {
            return DateUtils.formatDateTime(App.app, time, DateUtils.FORMAT_SHOW_WEEKDAY);
        }
    }, YEAR(-Integer.MAX_VALUE, -6) {
        @Override
        String getTime(long time) {
            return DateUtils.formatDateTime(App.app, time, DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_NUMERIC_DATE);
        }
    };

    abstract String getTime(long time);

    protected float start;
    protected float end;

    TimeFormat(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static String formatTime(long time) {
        float timeOffset = (time - getTimesMorning()) * 1f / DateUtils.DAY_IN_MILLIS;
        for (TimeFormat format : TimeFormat.values()) {
            if (timeOffset >= format.start && timeOffset < format.end) {
                return format.getTime(time);
            }
        }
        return TODAY.getTime(time);
    }

    public static long getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
