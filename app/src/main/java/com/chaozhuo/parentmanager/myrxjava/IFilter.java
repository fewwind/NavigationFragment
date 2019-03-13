package com.chaozhuo.parentmanager.myrxjava;

/**
 * Created by fewwind on 19-3-7.
 */

public interface IFilter<T> {
    boolean test(T t);
}
