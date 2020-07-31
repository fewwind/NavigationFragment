package com.feng.advance.weight;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    int mHeight;
    int mWidth;
    List<Rect> list = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int totalH = 0;
        int totalW = 0;
        int lineH = 0;
        int lineW = 0;

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            lineW += width;
            if (lineW <= mWidth) {
                lineH = Math.max(lineH, height);
                Rect rect = new Rect(lineW - width, totalH, lineW, totalH + height);
                list.add(rect);
                totalW = Math.max(lineW, totalW);
            } else {
                totalW = Math.max(lineW - width, totalW);
                lineW = 0;
                totalH += lineH;
                lineH = 0;

                lineW += width;
                lineH = Math.max(lineH, height);
                Rect rect = new Rect(lineW - width, totalH, lineW, totalH + height);
                list.add(rect);
            }
        }
        totalH += lineH;
        if (wMode == MeasureSpec.AT_MOST && hMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(Math.min(totalW, mWidth), Math.min(totalH, mHeight));
        } else if (wMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(Math.min(totalW, mWidth), mHeight);
        } else if (hMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, Math.min(totalH, mHeight));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            Rect rect = list.get(i);
            view.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }
}
