package com.chaozhuo.parentmanager.weight.touch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

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
        TouchUtil.dispatch(this, ev);
//        return false;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        TouchUtil.intercepert(this, ev);
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchUtil.touch(this, event);
        return super.onTouchEvent(event);
    }
}
