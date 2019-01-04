package com.chaozhuo.parentmanager.weight.window;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.util.DensityUtil;


/**
 * 窗口右侧的一个浮动按钮, 点击弹出设置界面.
 */
public class FloatView extends RelativeLayout {
    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private int mTouchDownX, mTouchDownY;
    private int mTouchSlopSquare;
    private boolean mAlwaysInTapRegion;
    private int mParamsX, mParamsY;

    private ImageView mIvLogo;

    public FloatView(Context context) {
        this(context, null);
    }

    public FloatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.float_view, this);

        mIvLogo = (ImageView) findViewById(R.id.float_logo);

        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        mParams = new WindowManager.LayoutParams();
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        mParams.width = LayoutParams.WRAP_CONTENT;
        mParams.height = LayoutParams.WRAP_CONTENT;
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        // 初始位置在屏幕左侧

        mParams.x = (int) (DensityUtil.width() * 0.4f - getMeasuredWidth() / 2);
        mParams.y = -(mIvLogo.getMeasuredHeight() / 2);
        setLayoutParams(mParams);
        int touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mTouchSlopSquare = touchSlop * touchSlop;
    }

    public int getFloatViewX() {
        return mParams.x;
    }

    public int getFloatViewY() {
        return mParams.y;
    }

    public void updatePositionX(float offsetX) {
        mParams.x = mParams.x + (int) offsetX;
        mWindowManager.updateViewLayout(this, mParams);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                mAlwaysInTapRegion = true;
                mTouchDownX = x;
                mTouchDownY = y;
                mParamsX = mParams.x;
                mParamsY = mParams.y;
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                if (mAlwaysInTapRegion) {
                    final int deltaX = x - mTouchDownX;
                    final int deltaY = y - mTouchDownY;
                    int distance = (deltaX * deltaX) + deltaY * deltaY;
                    if (distance > mTouchSlopSquare) {
                        mAlwaysInTapRegion = false;
                    }
                }
                if (!mAlwaysInTapRegion) {
                    updatePosition(mParamsX + x - mTouchDownX, mParamsY + y - mTouchDownY);
                }
            }
            break;
            case MotionEvent.ACTION_CANCEL: {
                mAlwaysInTapRegion = false;
            }
            break;
            case MotionEvent.ACTION_UP: {
                if (mAlwaysInTapRegion) {
                    if (mTouchDownX < getFloatViewX() + mIvLogo.getWidth()) {
                        ViewManager.get().exitShowMode();
                    }
                } else {
                    animateToSide();
                }
                mAlwaysInTapRegion = false;
            }
            break;
        }
        return true;
    }

    private void updatePosition(float x, float y) {
        if (x < 0) {
            x = 0;
        }
        int[] size = DensityUtil.getRealScreenSize();
        int right = size[0] - getWidth();
        if (x > right) {
            x = right;
        }
        try {
            mParams.x = (int) x;
            mParams.y = (int) y;
            mWindowManager.updateViewLayout(this, mParams);
        } catch (Exception e) {
        }
    }

    private void animateToSide() {
        ValueAnimator animator = ValueAnimator.ofInt(mParams.y, -(mIvLogo.getHeight() / 2));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                final int y = (int) animation.getAnimatedValue();
                mParams.y = y;
                mWindowManager.updateViewLayout(FloatView.this, mParams);
            }

            ;
        });
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

}
