package com.feng.advance.weight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.feng.advance.R;
import com.feng.advance.util.DensityUtil;
import java.util.ArrayList;
import java.util.List;

public class DotAnimView extends ViewGroup {

    int dotSize = DensityUtil.dip2px(8);
    List<View> mViews = new ArrayList<>();
    List<Animator> mAnims = new ArrayList<>();
    AnimatorSet animatorSet;

    public DotAnimView(Context context) {
        this(context, null);
    }


    public DotAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ViewGroup.LayoutParams params = new LayoutParams(dotSize, dotSize);
        for (int i = 0; i < 3; i++) {
            View v = new View(context);
            v.setBackgroundResource(R.drawable.shape_dot);
            mViews.add(v);
            addView(v, params);
        }
        for (View v :mViews) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v, View.ALPHA, 1f, 0.4f);
            objectAnimator.setDuration(500);
            mAnims.add(objectAnimator);
        }

        animatorSet = new AnimatorSet();
        animatorSet.playSequentially(mAnims);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet.start();
            }
        });
    }

    public void start(){
        animatorSet.start();
    }
    public void stop(){
        animatorSet.clone();
    }
    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        for (int i = 0; i < getChildCount(); i++) {
            left = i * 3 * dotSize;
            View child = getChildAt(i);
            child.layout(left, 0, left + dotSize, dotSize);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            setMeasuredDimension(dotSize * 7, dotSize);
        }
    }

}
