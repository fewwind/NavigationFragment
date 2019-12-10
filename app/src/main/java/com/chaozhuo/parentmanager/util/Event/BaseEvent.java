package com.chaozhuo.parentmanager.util.Event;

public class BaseEvent<T> {
    public String type;
    public T data;

    public BaseEvent(String type, T data) {
        this.type = type;
        this.data = data;
    }
}
