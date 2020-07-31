package com.feng.advance.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MutiCommonAdapterRV extends RecyclerView.Adapter<ViewHolderRV> {
    protected Context mContext;
    protected List<?> mDatas;
    protected List<ItemBinder> mBinders;
    protected LayoutInflater mInflater;

    public MutiCommonAdapterRV(Context context, List<?> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    public MutiCommonAdapterRV(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = new ArrayList<>();
    }

    public void setData(List<?> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    public void addViewHolder(ItemBinder binder) {
        if (mBinders == null) {
            mBinders = new ArrayList<>();
        }
        mBinders.add(binder);
    }

    @Override
    public ViewHolderRV onCreateViewHolder(final ViewGroup parent, int viewType) {
        ItemBinder itemBinder = mBinders.get(viewType);
        return itemBinder.creatHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolderRV holder, int position) {
        mBinders.get(holder.getItemViewType()).bindHolder(holder, mDatas.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return getType(mDatas.get(position));
    }

    private int getType(Object obj) {
        for (int i = 0; i < mBinders.size(); i++) {
            if (mBinders.get(i).getClassType().equals(obj.getClass())) return i;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
}