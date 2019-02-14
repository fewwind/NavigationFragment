package com.chaozhuo.parentmanager.weight.touch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-1-25.
 */

public class FrameLayoutB extends FrameLayout {
    public FrameLayoutB(@NonNull Context context) {
        super(context);
    }

    public FrameLayoutB(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameLayoutB(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.v(getClass().getSimpleName() + " = dispatch -- " + ev.toString());
        return false;
//        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logger.v(getClass().getSimpleName() + " = onIntercept -- " + ev.toString());
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.w(super.onTouchEvent(event)+getClass().getSimpleName() + " = onTouchEvent -- " + event.toString());
        return super.onTouchEvent(event);
    }
}
