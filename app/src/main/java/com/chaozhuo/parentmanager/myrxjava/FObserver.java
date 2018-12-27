package com.chaozhuo.parentmanager.myrxjava;

/**
 * Created by fewwind on 18-12-27.
 */

public interface FObserver<T> {
    void onNext(T t);

    void onError();

    void onComplete();

}
