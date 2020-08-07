package com.feng.advance.weight.touch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

//可以在垂直方向嵌套滚动的rv
public class NoScrollRv extends RecyclerView {
    public NoScrollRv(Context context) {
        super(context);
    }

    public NoScrollRv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    float lastY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if ((ev.getY() - lastY < 0) && !canScrollVertically(10)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                if ((ev.getY() - lastY > 0) && !canScrollVertically(-10)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
