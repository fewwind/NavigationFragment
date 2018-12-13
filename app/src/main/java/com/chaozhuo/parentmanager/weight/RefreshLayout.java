package com.chaozhuo.parentmanager.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 18-12-11.
 */

public class RefreshLayout extends LinearLayout {

    private HeadView mHeaderView;
    private Scroller mScroller;
    private int mHeight;
    private boolean isRefreshing;
    private int touchY;
    private int yP;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private View mScroll;

    public void initView() {
        setOrientation(VERTICAL);
        mScroller = new Scroller(getContext());
        mHeaderView = new HeadView(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHeaderView.getParent() != null) return;
        mHeaderView.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        mHeight = mHeaderView.getMeasuredHeight();
        Logger.v(getChildCount() + " = topmagin = " + mHeight);
        addView(mHeaderView, 0);
        MarginLayoutParams paramsLayout = (MarginLayoutParams) getLayoutParams();
        paramsLayout.topMargin = -mHeight;
        setLayoutParams(paramsLayout);
        mScroll = getChildAt(1);
    }
/*    public void setY(float y) {
        setTranslationY(y - mTop);
    }
    指的是view相对于父view顶部的绝对值，
    setTranslationY（）
    指的是相对于父view的偏移量
    假如当前view距离父view顶部200，如果setY（100）。此view会向上移动100，setTranslationY（100），次view会向下移动100
    setY()和scrollTo()很相似，都是向对于某个位置，二setTranslationY scrollBy都是绝对位置，指的是偏移量*/

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isRefreshing) return true;
        boolean vS = mScroll.canScrollVertically(-1);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchY = (int) ev.getY();
                yP = (int) ev.getY();
                Logger.w("ACTION_DOWN " + vS);
                return false;
            case MotionEvent.ACTION_MOVE:
                int offset = (int) (ev.getY() - yP);
//                boolean slop = ViewConfiguration.getTouchSlop() -Math.abs(offset);
                Logger.v(offset + " ACTION_MOVE " + vS);
                if (offset > 0 && !vS) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                Logger.i("ACTION_UP " + vS);
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchY = (int) ev.getY();
                Logger.e("onTouchEvent ACTION_DOWN " + touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                int offset = (int) (ev.getY() - touchY);
                MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
                Logger.d(layoutParams.topMargin + " = " + ev.getY() + " ** " + touchY + " ** " + offset);
                scrollTo(0, -offset / 2);

//                setY(offset/2);

//                scrollBy(0,-offset/2);
//                touchY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                touchY = 0;
                if (mHeight <= Math.abs(getScrollY()))
                    onRefresh();
                else reset();
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void onRefresh() {
        isRefreshing = true;
        Logger.v("开始刷新 = " + getScrollY());
        // finalY 指的是目的地坐标 - 当前滑动距离
        mScroller.startScroll(0, getScrollY(), 0, -mHeight - getScrollY());
        invalidate();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                reset();
                isRefreshing = false;
            }
        }, 1000);
    }

    public void reset() {
        mScroller.startScroll(0, getScrollY(), 0, -getScrollY());
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
        super.computeScroll();
    }
}
