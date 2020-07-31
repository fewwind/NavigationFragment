package com.feng.advance.copy2creat.Event.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.feng.advance.copy2creat.Event.BaseEvent;

import java.lang.reflect.Field;

public class EventLiveData2<T> extends MutableLiveData<BaseEvent<T>> {
    public static final EventLiveData DATA = new EventLiveData();

    public void dispatch(String key, T data) {
        setValue(new BaseEvent(key, data));
    }

    public void addObserve(LifecycleOwner owner, LiveObserver<T> observer) {
        try {
            Field mVersion = LiveData.class.getDeclaredField("mVersion");
            mVersion.setAccessible(true);
            Object oldVersion = mVersion.get(this);
            mVersion.set(this, Integer.MIN_VALUE);
            super.observe(owner, observer);
            mVersion.set(this, oldVersion);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
