package com.chaozhuo.parentmanager.myrxjava;

/**
 * Created by fewwind on 18-12-27.
 */

public interface FObservableOnSubscribe<T> {
    void subscribe(FObserver<T> observer);
}
