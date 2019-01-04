package com.chaozhuo.parentmanager.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by ryanhuen on 18-10-8.
 */

public class AppInfoBean {
    public Drawable imageDrawable;
    public String title;
    public String pkgName;
    public String activityName;
    public int order;


    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }


    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppInfoBean bean = (AppInfoBean) o;

        return pkgName != null ? pkgName.equals(bean.pkgName) : bean.pkgName == null;
    }

    @Override
    public String toString() {
        return "AppInfoBean{" +
                "title='" + title + '\'' +
                ", pkgName='" + pkgName + '\'' +
                '}';
    }
}
