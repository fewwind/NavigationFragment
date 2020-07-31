package com.feng.advance.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feng.advance.R;
import com.feng.advance.util.DensityUtil;

/**
 * Created by zpf on 2018/5/25.
 */

public class CustomSeekBarV extends FrameLayout {

    public interface onValueChange {
        void onChange(float Percent);
    }

    private onValueChange l;

    private FrameLayout mainLayout;

    private ImageView gea_seekbar_imgview;

    private Paint mPaint;

    private int mRoundRadius = DensityUtil.dip2px(12);
    int fillColor = Color.parseColor("#d3d3d3");

    /**
     * The values range from 0 to 1.
     **/
    private float mCurrentSeekValuePercent = 0.7f;

    private int mMeasureHeight, mMeasureWidth;

    public CustomSeekBarV(@NonNull Context context) {
        this(context,null);
    }

    public CustomSeekBarV(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setListener(onValueChange listener) {
        this.l = listener;
    }

    public void setImgBg(int ResourceId) {
        if (gea_seekbar_imgview != null) {
            gea_seekbar_imgview.setImageResource(ResourceId);
        }
    }

    /**
     * set value by custom
     **/
    public boolean setSeekValuePercent(float value) {
        if (value < 0 || value > 1) {
            return false;
        }
        if (this.mCurrentSeekValuePercent != value) {
            this.mCurrentSeekValuePercent = value;
            postInvalidate();
        }
        return true;
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mainLayout == null) {
            mainLayout = (FrameLayout) inflater.inflate(R.layout.custom_seekbar_v, this, true);
            setLayoutBG(mainLayout);
            gea_seekbar_imgview = (ImageView) mainLayout.findViewById(R.id.seekbar_imgview);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(Color.WHITE);
            mPaint.setAntiAlias(true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawByValue(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasureHeight = getMeasuredHeight();
        mMeasureWidth = getMeasuredWidth();
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            mMeasureWidth = DensityUtil.dip2px(48);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            mMeasureHeight = DensityUtil.dip2px(240);
        }
        setMeasuredDimension(mMeasureWidth, mMeasureHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float dy = event.getY();
        if (dy < mRoundRadius / 2) {
            mCurrentSeekValuePercent = 1;
            postInvalidate();
            return super.onTouchEvent(event);
        }
        if (dy > mMeasureHeight - mRoundRadius / 2) {
            mCurrentSeekValuePercent = 0;
            postInvalidate();
            return super.onTouchEvent(event);
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mCurrentSeekValuePercent = (mMeasureHeight - dy) / mMeasureHeight;
            postInvalidate();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mCurrentSeekValuePercent = (mMeasureHeight - dy) / mMeasureHeight;
            postInvalidate();
        }
        return super.onTouchEvent(event);
    }


    /**
     * set layout bg
     **/
    private void setLayoutBG(FrameLayout layout) {

        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setCornerRadius(mRoundRadius);
        layout.setBackground(gd);
    }


    float[] radiiProgress = {0f, 0f, 0f, 0f, mRoundRadius, mRoundRadius, mRoundRadius, mRoundRadius};
    float[] radiiZero = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};
    float[] radiiFull = {mRoundRadius, mRoundRadius, mRoundRadius, mRoundRadius, mRoundRadius, mRoundRadius, mRoundRadius, mRoundRadius};

    /**
     * Draw progress according to the current value
     **/
    private void drawByValue(Canvas canvas) {
        float realValue = mMeasureHeight - mCurrentSeekValuePercent * mMeasureHeight;

        RectF r = new RectF(0, realValue, mMeasureWidth, mMeasureHeight);
        Path path = new Path();
        if (realValue < mRoundRadius / 2) {
            path.addRoundRect(r, radiiFull, Path.Direction.CW);
        } else {
            path.addRoundRect(r, radiiProgress, Path.Direction.CW);
        }
        canvas.drawPath(path, mPaint);
        if (this.l != null) {
            this.l.onChange(mCurrentSeekValuePercent);
        }
    }
}
