package com.chaozhuo.parentmanager.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.orhanobut.logger.Logger;

public class ExpendLayout extends FrameLayout {
    private boolean expend = false;
    private int height;

    public ExpendLayout(@NonNull Context context) {
        this(context, null);
    }

    public ExpendLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 不可行，会有一帧闪烁
   /*     post(new Runnable() {
            @Override
            public void run() {
                height = getMeasuredHeight();
                expend = true;
                showAnim(10);
                Logger.e("Runnable - " + getMeasuredHeight());
            }
        });
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Logger.e("hi - " + getMeasuredHeight());
                height = getMeasuredHeight();
//                setHeight(0);
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });*/
    }

    public void setExpend(boolean state) {
        expend = state;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void showAnim() {
        showAnim(360);
    }

    public void showAnim(int time) {
        ValueAnimator animator = expend ? ValueAnimator.ofInt(height, 0) : ValueAnimator.ofInt(0, height);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setHeight((int) animation.getAnimatedValue());
            }
        });
        animator.setDuration(time);
        animator.start();
        expend = !expend;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (height == 0) {
            height = getMeasuredHeight();
            if (!expend) setMeasuredDimension(getMeasuredWidth(), 0);
        }
        Logger.w(height + "()" + getMeasuredHeight());
    }


    public void setHeight(int height) {
        getLayoutParams().height = height;
        requestLayout();
    }

}
