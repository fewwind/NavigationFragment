package com.feng.advance.copy2creat.Event.livedata;

import android.arch.lifecycle.MutableLiveData;

import com.feng.advance.copy2creat.Event.BaseEvent;

public class EventLiveData<T> extends MutableLiveData<BaseEvent> {
    public static final EventLiveData DATA = new EventLiveData();

    public void dispatch(String key, T data) {
        setValue(new BaseEvent(key, data));
    }
}
