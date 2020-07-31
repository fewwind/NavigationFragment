package com.feng.advance.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CardStackView extends View {

    int height = 200;
    int width = 0;
    int wOffset = 16;
    int hOffset = 32;
    int count = 5;

    List<Path> mData = new ArrayList<>();
    int[] mColor = {Color.BLACK, Color.YELLOW, Color.CYAN, Color.RED, Color.BLUE};
    Paint mPaint;

    public CardStackView(Context context) {
        this(context, null);
    }

    public CardStackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        if (mData.isEmpty())
            for (int i = count; i > 0; i--) {
                RectF rectF = new RectF(i * wOffset, (count - i + 1) * hOffset, width - i * wOffset, (count - i + 1) * hOffset + height);
                Path path = new Path();
                path.addRoundRect(rectF, 50, 50, Path.Direction.CW);
                mData.add(path);
            }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getMeasuredWidth(), hOffset * count + height);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mData.size(); i++) {
            Path path = mData.get(i);
            mPaint.setColor(mColor[i]);
            canvas.drawPath(path, mPaint);
        }
    }
}
