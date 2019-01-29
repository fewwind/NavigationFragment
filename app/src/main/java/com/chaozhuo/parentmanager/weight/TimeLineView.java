package com.chaozhuo.parentmanager.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.util.DensityUtil;
import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-1-24.
 */

public class TimeLineView extends FrameLayout implements View.OnTouchListener {
    ImageView mBtnLeft;
    ImageView mBtnRight;
    View mShadowLeft;
    View mShadowRight;
    int margin = DensityUtil.dip2px(16);

    public TimeLineView(@NonNull Context context) {
        this(context, null);
    }

    public TimeLineView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_timeline_edit, this, true);
        mBtnLeft = findViewById(R.id.edit_btn_left);
        mBtnRight = findViewById(R.id.edit_btn_right);
        mShadowLeft = findViewById(R.id.edit_shadow_left);
        mShadowRight = findViewById(R.id.edit_shadow_right);
        mBtnLeft.setOnTouchListener(this);
        mBtnRight.setOnTouchListener(this);
    }

    int leftX;
    int rightX;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                leftX = x;
                Logger.v("Down = " + leftX);
                break;
            case MotionEvent.ACTION_MOVE:
                int curr = (int) event.getX();
//                        if (curr < ) curr = DensityUtil.dp2px(16);
//                        v.offsetLeftAndRight(curr - leftX);
//                Logger.i((-curr + leftX) + " = Move = " + mBtnRight.getLeft());

                switch (v.getId()) {
                    case R.id.edit_btn_left:
                        updateSeekLeft(curr - leftX);
                        break;
                    case R.id.edit_btn_right:
                        updateSeekRight(-curr + leftX);
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void updateSeekLeft(int offset) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) mBtnLeft.getLayoutParams();
        layoutParams.leftMargin += offset;
        if (layoutParams.leftMargin < 0) layoutParams.leftMargin = 0;
        if (layoutParams.leftMargin + margin > mBtnRight.getLeft())
            layoutParams.leftMargin = mBtnRight.getLeft() - margin;
        mBtnLeft.setLayoutParams(layoutParams);
        updateShadowLeft();
    }

    private void updateSeekRight(int offset) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) mBtnRight.getLayoutParams();
        Logger.v(mBtnLeft.getRight() + " = " + mShadowLeft.getWidth());
        layoutParams.rightMargin += offset;
        if (layoutParams.rightMargin < 0) layoutParams.rightMargin = 0;
        int left = ((MarginLayoutParams) mBtnLeft.getLayoutParams()).leftMargin;
        if (layoutParams.rightMargin + margin > (getMeasuredWidth() - left - margin)) {
            layoutParams.rightMargin = getMeasuredWidth() - left - margin * 2;
        }
        mBtnRight.setLayoutParams(layoutParams);
        updateShadowRight();
    }

    private void updateShadowLeft() {
        MarginLayoutParams params = (MarginLayoutParams) mShadowLeft.getLayoutParams();
        params.leftMargin = margin;
        params.width = ((MarginLayoutParams) mBtnLeft.getLayoutParams()).leftMargin - DensityUtil.dip2px(16);
        params.width = Math.max(params.width, 0);
        mShadowLeft.setLayoutParams(params);
    }

    private void updateShadowRight() {
        MarginLayoutParams params = (MarginLayoutParams) mShadowRight.getLayoutParams();
        params.rightMargin = margin;
        params.width = ((MarginLayoutParams) mBtnRight.getLayoutParams()).rightMargin - DensityUtil.dip2px(16);
        params.width = Math.max(params.width, 0);
        mShadowRight.setLayoutParams(params);
    }
}
