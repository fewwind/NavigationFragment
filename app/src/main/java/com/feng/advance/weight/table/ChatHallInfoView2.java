package com.feng.advance.weight.table;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.feng.advance.base.ViewHolderRV;
import com.feng.advance.weight.adapter.HallDataObserver;
import com.shuo.ruzuo.chat.adapter.BaseHallAdapter;

public class ChatHallInfoView2 extends BaseChatHallView2<View> implements HallDataObserver {

    public ChatHallInfoView2(Context context) {
        this(context, null);
    }

    public ChatHallInfoView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    BaseHallAdapter mAdapter;

    /**
     * 必须保证adapter有数据，否则view无法显示
     */
    public void setAdapter(BaseHallAdapter adapter) {
        if (getChildCount() == 0) throw new IllegalStateException("first call setHallBg");
//        if (mAdapter != null) mAdapter.unRegisterAdapterObserver(this);
        this.mAdapter = adapter;
        mAdapter.registerAdapterObserver(this);
        addChildrenData(adapter);
//        requestLayout();
    }

    public BaseHallAdapter getmAdapter() {
        return mAdapter;
    }

    private void addChildrenData(BaseHallAdapter adapter) {
        int count = adapter.getCount();
        ViewGroup.LayoutParams paramItem = new ViewGroup.LayoutParams(itemW, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (getChildCount() > 1) {
            for (int i = 1; i < getChildCount(); i++) {
                removeViewAt(i);
            }
        }
        mHolders.clear();
        for (int i = 0; i < count; i++) {
            ViewHolderRV holder = adapter.creatViewHolder(this, i);
            mHolders.put(i, holder);
            addView(holder.itemView, paramItem);
            adapter.bindViewholder(holder, i);
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
//        addChildrenData(mAdapter);
    }

    public ViewGroup getItemView(int pos) {
        return (ViewGroup) mHolders.get(pos).itemView;
    }
    @Override
    public void notifyDataItem(int pos) {
        mAdapter.bindViewholder(mHolders.get(pos), pos);
    }

}
