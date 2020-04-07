package com.chaozhuo.parentmanager.weight.touch;

import android.view.MotionEvent;
import android.view.View;

import com.chaozhuo.parentmanager.App;
import com.orhanobut.logger.Logger;

public class TouchUtil {
    static boolean eventLog = true;

    public static void dispatch(Object view, MotionEvent event) {
        if (eventLog) Logger.d(getName(event) + "-->dispatch-->" + view.getClass().getSimpleName());
    }

    public static void intercepert(Object view, MotionEvent event) {
        if (eventLog)
            Logger.i(getName(event) + "-->Intercept-->" + view.getClass().getSimpleName());
    }

    public static void touch(Object view, MotionEvent event) {
        if (eventLog)
            Logger.e(getName(event) + "-->TouchEvent-->" + view.getClass().getSimpleName());
    }

    public static String getName(MotionEvent event) {
        String name = "No_Action";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                name = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                name = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                name = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                name = "ACTION_CANCEL";
                break;
        }
        return name;
    }

    public static void log(View view, String event) {
        Logger.i(view.getClass().getSimpleName() + ":> " + App.app.getResources().getResourceName(view.getId()) + "-> " + event);
    }
}
