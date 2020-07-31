package com.feng.advance.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fewwind on 18-12-17.
 */
public class AppUsageTotalBean implements Serializable {
    public static String PRIOD_DAY = "day";
    public static String PRIOD_WEEK = "week";
    public long totalTime;
    public List<AppUsageBean> usage;
    public String priod;

    @Override
    public String toString() {
        return "AppUsageTotalBean{" +
                "totalTime=" + totalTime +
                ", usage=" + usage +
                ", isToday=" + priod +
                '}';
    }
}
