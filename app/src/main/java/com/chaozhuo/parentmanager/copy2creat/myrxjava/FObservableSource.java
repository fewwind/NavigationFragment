package com.chaozhuo.parentmanager.copy2creat.myrxjava;

/**
 * Created by fewwind on 19-3-7.
 */

public interface FObservableSource<T> {
    void subscribe(FObserver<T> observer);
}
