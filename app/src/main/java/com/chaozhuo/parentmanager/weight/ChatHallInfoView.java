package com.chaozhuo.parentmanager.weight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.weight.adapter.BaseHallAdapter;
import com.chaozhuo.parentmanager.weight.adapter.HallDataObserver;
import com.chaozhuo.parentmanager.weight.adapter.HallSize;

public class ChatHallInfoView extends ViewGroup implements HallDataObserver {
    int radius;
    int itemW;
    int itemH;
    int avatarRadius;
    int offsetLeft;
    int width;
    int offsetTop;
    private HallSize hallSize;
    int bgRes;
    Context context;
    SparseArray<RecyclerView.ViewHolder> mHolders = new SparseArray<>();

    public ChatHallInfoView(Context context) {
        this(context, null);
    }

    public ChatHallInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        ViewGroup.LayoutParams paramItem = new ViewGroup.LayoutParams(itemW, LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < 6; i++) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.view_broadcast_info, null);
            inflate.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

//            addView(inflate, paramItem);
        }
//        setOffsetTop(DensityUtil.dip2px(24));
    }

    public void setHallBg(HallSize size, int resId) {
        if (hallSize == null) {
            hallSize = size;
            bgRes = resId;
            itemW = size.getItemW();
            radius = size.getBgRadius();
            avatarRadius = size.getAvatarRadius();
            width = size.getWidth();
            offsetLeft = itemW / 2 - avatarRadius;
            View view = new View(context);
            view.setBackgroundResource(resId);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, radius * 2);
            addView(view, 0, params);
        }
    }

    public void setOffsetTop(int top) {
        offsetTop = top;
    }

    public void updateUI() {
        for (int i = 1; i < getChildCount(); i++) {
            View v = getChildAt(i);
            ((TextView) v.findViewById(R.id.name)).setText("info - " + i);
        }
    }

    BaseHallAdapter mAdapter;

    public void setAdapter(BaseHallAdapter adapter) {
        if (getChildCount() == 0) throw new IllegalStateException("first call setHallBg");
        if (mAdapter != null) mAdapter.unRegisterAdapterObserver(this);
        this.mAdapter = adapter;
        mAdapter.registerAdapterObserver(this);
        addChildrenData(adapter);
        requestLayout();
    }

    private void addChildrenData(BaseHallAdapter adapter) {
        int count = adapter.getCount();
        ViewGroup.LayoutParams paramItem = new ViewGroup.LayoutParams(itemW, LayoutParams.WRAP_CONTENT);
        if (getChildCount() > 1) {
            for (int i = 1; i < getChildCount(); i++) {
                removeViewAt(i);
            }
        }
        for (int i = 0; i < count; i++) {
            RecyclerView.ViewHolder holder = adapter.creatViewHolder(this);
            adapter.bindViewholder(holder, i);
            mHolders.put(i, holder);
            addView(holder.itemView, paramItem);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAdapter != null) mAdapter.unRegisterAdapterObserver(this);
    }

    @Override
    public void notifyDataChange() {
        for (int i = 0; i < mHolders.size(); i++) {
            mAdapter.bindViewholder(mHolders.get(i), i);
        }
    }

    @Override
    public void notifyDataItem(int pos) {
        mAdapter.bindViewholder(mHolders.get(pos), pos);
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
