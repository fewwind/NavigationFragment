package com.chaozhuo.parentmanager.myrxjava;

import io.reactivex.Scheduler;

/**
 * Created by fewwind on 18-12-27.
 */

public abstract class FObservable<T> implements FObservableSource<T>{

    public static <T> FObservable<T> creat(FObservableOnSubscribe<T> fObservableOnSubscribe) {
        return new FCreatObservable<T>(fObservableOnSubscribe);
    }

    protected abstract void subscribeActual(FObserver<T> observer);

    @Override
    public void subscribe(FObserver<T> observer) {
        subscribeActual(observer);
    }

//    public final void subscribe(FObserver<T> observer) {
//        subscribeActual(observer);
//    }

    public final FObservable<T> observeOn(Scheduler scheduler) {
        return new FObservalbeObserveOn<T>(this, scheduler);
    }
    public FObservable<T> filter(IFilter<T> filter){
        return new FObservableFilter<T>(this,filter);
    }
}
