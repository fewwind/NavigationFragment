package com.chaozhuo.parentmanager.myrxjava;

/**
 * Created by fewwind on 18-12-27.
 */

public class FCreatObservable<T> extends FObservable<T> {
    public FObservableOnSubscribe<T> source;

    public FCreatObservable(FObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(FObserver<T> observer) {
        source.subscribe(observer);
    }
}
