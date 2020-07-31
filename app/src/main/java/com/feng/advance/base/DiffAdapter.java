package com.feng.advance.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.feng.advance.bean.TestBean;
import com.feng.advance.util.MyDiffT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fewwind on 18-12-6.
 */

public abstract class DiffAdapter<T extends TestBean> extends RecyclerView.Adapter<ViewHolderRV> {
    AsyncListDiffer<T> mDiffer;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mLayoutId;

    public DiffAdapter(Context context, int layoutId) {
        mDiffer = new AsyncListDiffer<T>(this, new MyDiffT<T>());
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mLayoutId = layoutId;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRV holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            convertDiff(holder, bundle.getInt("value"));
        }
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRV viewHolderRV, int i) {
        convert(viewHolderRV, mDiffer.getCurrentList().get(i));
    }

    public abstract void convert(ViewHolderRV holderRV, T t);

    void convertDiff(ViewHolderRV holderRV, int t) {

    }

    public void setData(T mData){
        List<T> mList = new ArrayList<>();
        mList.addAll(mDiffer.getCurrentList());
        mList.add(0,mData);
        mDiffer.submitList(mList);
    }
    public void setData(List<T> datas) {
        List<T> mList = new ArrayList<>();
        mList.addAll(datas);
        mDiffer.submitList(mList);
    }

    @NonNull
    @Override
    public ViewHolderRV onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return ViewHolderRV.createViewHolder(mContext, viewGroup, mLayoutId);
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }
}
