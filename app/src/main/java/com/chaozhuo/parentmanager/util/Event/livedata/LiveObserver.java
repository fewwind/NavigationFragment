package com.chaozhuo.parentmanager.util.Event.livedata;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.chaozhuo.parentmanager.util.Event.BaseEvent;

public abstract class LiveObserver<T> implements Observer<BaseEvent<T>> {
    public String key;

    public LiveObserver(String key) {
        this.key = key;
    }

    @Override
    public void onChanged(@Nullable BaseEvent<T> event) {
        if (event.type.equals(key)) {
            try {
                dataChange(event.data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void dataChange(T t);
}
