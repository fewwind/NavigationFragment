package com.chaozhuo.parentmanager.copy2creat.Event;


import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;

public class EventDispatch {

    ArrayMap<String, List<EventObserver>> mObserver;

    private static class Single {
        private static EventDispatch instance = new EventDispatch();
    }

    private EventDispatch() {
        mObserver = new ArrayMap<>();
    }

    public static EventDispatch get() {
        return Single.instance;
    }


    public synchronized void addListener(EventObserver observer) {
        if (mObserver.get(observer.eventType) == null) {
            List<EventObserver> observers = new ArrayList<>();
            observers.add(observer);
            mObserver.put(observer.eventType, observers);
        } else {
            mObserver.get(observer.eventType).add(observer);
        }
    }

    public synchronized void removeListener(EventObserver observer) {
        mObserver.get(observer.eventType).remove(observer);
    }

    public <T> void dispatch(BaseEvent<T> event) {
        if (mObserver.get(event.type) == null) return;
        for (EventObserver observer : mObserver.get(event.type)) {
            observer.listener.onEventListener(event);
        }
    }
}
