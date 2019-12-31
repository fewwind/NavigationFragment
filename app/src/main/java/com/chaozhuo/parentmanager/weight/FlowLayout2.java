package com.chaozhuo.parentmanager.weight;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout2 extends ViewGroup {
    int mHeight;
    int mWidth;
    List<Rect> list = new ArrayList<>();
    int space = 16;

    public FlowLayout2(Context context) {
        super(context);
    }

    public FlowLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int totalH = 0;
        int totalW = 0;
        int lineH = 0;
        int lineW = space;

        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
//            lineW += (width + space);
            if (lineW + width + space > mWidth) {
                lineW = space;
                totalH += lineH;
                lineH = 0;
            }
            lineW += (width + space);
            lineH = Math.max(lineH, height + space);
            Rect rect = new Rect(lineW - width - space, totalH + space, lineW - space, totalH + height + space);
            list.add(rect);
            totalW = Math.max(lineW, totalW);
        }
        totalH += (lineH + space);
        Logger.v(totalW + "<>" + totalH);
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

    public <T> void setAdapter(List<T> data, ViewH<T> viewH) {
        removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
            viewH.convert(data.get(i), view, i);
            addView(view);
        }
    }

    public static abstract class ViewH<T> {
        public abstract void convert(T item, View view, int pos);
    }
}
