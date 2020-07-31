package com.feng.advance.copy2creat.myrxjava;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 18-12-27.
 */

public class FCreatObservable<T> extends FObservable<T> {
    public FObservableOnSubscribe<T> source;

    public FCreatObservable(FObservableOnSubscribe<T> source) {
        this.source = source;
        Logger.d("New FcreatObservable = " + source);
    }

    @Override
    protected void subscribeActual(FObserver<T> observer) {
        Logger.d("FcreatObservable subscribeActual = " + observer);
        source.subscribe(observer);
    }
}
