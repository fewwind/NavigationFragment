package com.feng.advance.base;

import android.content.Context;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

public class ItemBinder<T> {
    public Class<T> tClass;
    public ViewHolderRV holderRV;
    public int itemId;
    public Context context;


    public ItemBinder(Context context, int itemId) {
        this.context = context;
        this.itemId = itemId;
    }

    public ItemBinder(Context context, Class<T> clazz, int itemId) {
        this.context = context;
        this.itemId = itemId;
        this.tClass = clazz;
    }

    public ViewHolderRV creatHolder(ViewGroup parent) {
        this.holderRV = ViewHolderRV.createViewHolder(context, parent, itemId);
        return holderRV;
    }

    public void bindHolder(ViewHolderRV holderRV, T t) {
    }

    public Class<T> getClassType() {
        if (tClass == null) {
            tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return tClass;
    }
}
