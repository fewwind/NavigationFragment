package com.feng.advance.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.feng.common.BaseApp;

public class DensityUtil {

    public static int dip2px(float dpValue) {
        final float scale = BaseApp.app.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int W() {
        return getRealScreenSize()[1];
    }

    public static int H() {
        return getRealScreenSize()[0];
    }

    public static int height() {
        return BaseApp.app.getResources().getDisplayMetrics().heightPixels;
    }

    public static int width() {
        return BaseApp.app.getResources().getDisplayMetrics().widthPixels;
    }

    // 获取不包含边界的间距
    public static int getGridPadding(int count, int widthDP, int allWidth) {
        return allWidth - widthDP * count / (count - 1);
    }

    public static int[] getRealScreenSize() {
        WindowManager wm = (WindowManager) BaseApp.app.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        return new int[]{point.x, point.y};
    }
}