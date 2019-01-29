package com.chaozhuo.parentmanager.weight.touch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-1-25.
 */

public class ViewC extends View {
    public ViewC(@NonNull Context context) {
        super(context);
    }

    public ViewC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.d(getClass().getSimpleName() + " = dispatch -- " + ev.toString());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.w(getClass().getSimpleName() + " = onTouchEvent -- " + event.toString());
//        return super.onTouchEvent(event);
        return true;
    }
}
