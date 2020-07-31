package com.feng.advance.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.feng.advance.bean.TestBean;

/**
 * Created by fewwind on 18-12-6.
 */

public class MyDiff extends DiffUtil.ItemCallback<TestBean> {
    @Override
    public boolean areItemsTheSame(@NonNull TestBean testBean, @NonNull TestBean t1) {
        return testBean.id == t1.id;
    }

    @Override
    public boolean areContentsTheSame(@NonNull TestBean testBean, @NonNull TestBean t1) {
        return testBean.value == t1.value;
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull TestBean oldItem, @NonNull TestBean newItem) {
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
