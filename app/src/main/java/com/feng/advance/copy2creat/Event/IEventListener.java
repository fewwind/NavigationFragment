package com.feng.advance.copy2creat.Event;

public interface IEventListener<T> {
    void onEventListener(BaseEvent<T> event);
}
