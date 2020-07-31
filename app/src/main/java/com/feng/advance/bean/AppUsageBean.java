package com.feng.advance.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by fewwind on 18-12-17.
 */
public class AppUsageBean implements Serializable {
    public transient Drawable drawable;
    public String name;
    public String time;
    public String pkg;
    public String icon;
    public int progress;

    @Override
    public String toString() {
        return "AppUsageBean{" +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", pkg='" + pkg + '\'' +
                ", progress=" + progress +
                '}';
    }
}
