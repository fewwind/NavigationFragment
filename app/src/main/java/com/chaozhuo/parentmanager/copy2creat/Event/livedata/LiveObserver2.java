package com.chaozhuo.parentmanager.copy2creat.Event.livedata;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.chaozhuo.parentmanager.copy2creat.Event.BaseEvent;

public abstract class LiveObserver2<T> implements Observer<BaseEvent<T>> {
    public String key;

    public LiveObserver2(String key) {
        this.key = key;
    }

    @Override
    public void onChanged(@Nullable BaseEvent<T> event) {
        if (event.type.equals(key)) {
            dataChange(event.data);
        }
    }

    public abstract void dataChange(T t);

}
