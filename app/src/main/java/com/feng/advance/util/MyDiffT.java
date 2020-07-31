package com.feng.advance.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.feng.advance.bean.TestBean;

/**
 * Created by fewwind on 18-12-6.
 */

public class MyDiffT<T extends TestBean> extends DiffUtil.ItemCallback<T> {
    @Override
    public boolean areItemsTheSame(@NonNull T testBean, @NonNull T t1) {
//        return false;
        return testBean.id == t1.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull T testBean, @NonNull T t1) {
        return testBean.value == t1.value;
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull T oldItem, @NonNull T newItem) {
        if (true) return null;
        Bundle bundle = new Bundle();
        if (oldItem.value != newItem.value) {
            bundle.putInt("value", newItem.value);
            return bundle;
        }
        if (bundle.size() == 0) {
            return null;
        }
        return bundle;
    }
}
