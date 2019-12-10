package com.chaozhuo.parentmanager.util.Event;

public interface IEventListener<T> {
    void onEventListener(BaseEvent<T> event);
}
