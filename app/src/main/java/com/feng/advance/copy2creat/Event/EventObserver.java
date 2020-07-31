package com.feng.advance.copy2creat.Event;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public class EventObserver implements LifecycleObserver {
    IEventListener listener;
    String eventType;

    public EventObserver(String type, IEventListener listener) {
        this.listener = listener;
        this.eventType = type;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void creat() {
        EventDispatch.get().addListener(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory() {
        EventDispatch.get().removeListener(this);
    }
}
