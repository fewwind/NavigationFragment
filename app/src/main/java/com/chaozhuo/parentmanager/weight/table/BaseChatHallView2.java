package com.chaozhuo.parentmanager.weight.table;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chaozhuo.parentmanager.base.ViewHolderRV;
import com.chaozhuo.parentmanager.weight.adapter.HallSize;

public class BaseChatHallView2<BG extends View> extends ViewGroup {
    int radius;
    int itemW;
    int itemH;
    int avatarRadius;
    int offsetLeft;
    int width;
    int offsetTop;
    BG bgView;
    protected HallSize hallSize;
    Context context;
    LayoutInflater inflater;
    SparseArray<ViewHolderRV> mHolders = new SparseArray<>();

    public BaseChatHallView2(Context context) {
        this(context, null);
    }

    public BaseChatHallView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setHallBg(HallSize size, BG t) {
        if (hallSize == null) {
            hallSize = size;
            itemW = size.getItemW();
            radius = size.getBgRadius();
            avatarRadius = size.getAvatarRadius();
            width = size.getWidth();
            offsetLeft = itemW / 2 - avatarRadius;
            LayoutParams params = new LayoutParams(width, radius * 2);
            addView(t, 0, params);
            bgView = t;
            addItemView();
        }
    }

    public BG getBgBiew() {
        return bgView;
    }
    protected void addItemView() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            measureChild(v, widthMeasureSpec, heightMeasureSpec);
            if (i == 0) continue;
            itemH = Math.max(itemH, v.getMeasuredHeight());
        }
        setMeasuredDimension(radius * 4 + itemW, radius * 2 + itemH);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int halfW = itemW / 2;

        //竖向分为4个坐标，横向分为3个
//        int left0 = 0;
//        int left1 = itemW / 2 + radius - (itemW / 2 - avatarRadius) - avatarRadius / 2;
//        int left2 = radius * 3 - itemW / 2 + (itemW / 2 - avatarRadius) + avatarRadius / 2;
//        int left3 = radius * 4;
//        int top0 = 0;
//        int top1 = radius;
//        int top2 = radius * 2;

        int left0 = 0;
        int left1 = itemW / 2 + radius - (itemW / 2 - avatarRadius) - avatarRadius / 2;
        int left2 = width + itemW / 2 - radius - itemW + (itemW / 2 - avatarRadius) + avatarRadius / 2;
        int left3 = width;
        int top0 = 0;
        int top1 = radius;
        int top2 = radius * 2;
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (i == 0) {
                v.layout(itemW / 2, offsetTop + avatarRadius, itemW / 2 + v.getMeasuredWidth(), offsetTop + avatarRadius + v.getMeasuredHeight());
            } else if (i == 1) {
                v.layout(left1, top0, left1 + itemW, top0 + v.getMeasuredHeight());
            } else if (i == 2) {
                v.layout(left2, top0, left2 + itemW, top0 + v.getMeasuredHeight());
            } else if (i == 3) {
                v.layout(left3, top1, left3 + itemW, top1 + v.getMeasuredHeight());
            } else if (i == 4) {
                v.layout(left2, top2, left2 + itemW, top2 + v.getMeasuredHeight());
            } else if (i == 5) {
                v.layout(left1, top2, left1 + itemW, top2 + v.getMeasuredHeight());
            } else if (i == 6) {
                v.layout(left0, top1, left0 + itemW, top1 + v.getMeasuredHeight());
            }
        }
    }

}
