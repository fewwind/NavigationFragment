package com.feng.advance.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

public abstract class BaseNineBlockLayout extends FrameLayout {
    private int itemWidth = 200;
    private int space = 16;
    public static int COLUMN = 3;
    private int visibleSize = 0;

    public BaseNineBlockLayout(@NonNull Context context) {
        this(context, null);
    }

    public BaseNineBlockLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutParams params = new LayoutParams(itemWidth, itemWidth);
        for (int i = 0; i < COLUMN * 3; i++) {
            View view = getView();
            addView(view, params);
        }
    }

    abstract View getView();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = (visibleSize - 1) / COLUMN;
        if (visibleSize != 0)
            setMeasuredDimension(itemWidth * COLUMN + space * (COLUMN - 1), (i + 1) * itemWidth + space * i);
    }

    public <T extends View, D> void setData(List<D> data, IBindView<T, D> iBindView) {
        visibleSize = data.size();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (i >= visibleSize) {
                view.setVisibility(GONE);
            } else {
                view.setVisibility(VISIBLE);
                iBindView.bind((T) view, data.get(i));
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            int indexW = i % COLUMN;
            int indexH = i / COLUMN;
            View view = getChildAt(i);
            int leftChild = indexW * itemWidth + indexW * space;
            int topChild = indexH * itemWidth + indexH * space;
            view.layout(leftChild, topChild, leftChild + itemWidth, topChild + itemWidth);
        }
    }

    public interface IBindView<T extends View, D> {
        void bind(T t, D data);
    }
}
