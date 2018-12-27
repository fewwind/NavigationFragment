package com.chaozhuo.parentmanager.myrxjava;

import io.reactivex.Scheduler;

/**
 * Created by fewwind on 18-12-27.
 */

public class FObservalbeObserveOn<T> extends FObservable<T> {
    private FObservable<T> source;

    public FObservalbeObserveOn(FObservable<T> source, Scheduler scheduler) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(FObserver<T> observer) {
        source.subscribe(observer);
    }
}
