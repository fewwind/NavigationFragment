package com.chaozhuo.parentmanager.copy2creat.myrxjava;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-3-7.
 */

public class FObservableFilter<T> extends FObservable<T> {
    public IFilter<T> mFilter;
    public FObservableSource<T> source;

    public FObservableFilter(FObservableSource<T> source, IFilter<T> mFilter) {
        this.mFilter = mFilter;
        this.source = source;
        Logger.i("new FObservableFilter "+source);
    }

    @Override
    protected void subscribeActual(FObserver<T> observer) {
        Logger.i("FObservableFilter subscribeActual"+observer);
        source.subscribe(new FilterObserver<T>(observer, mFilter));
    }

    class FilterObserver<T> implements FObserver<T> {
        public FObserver<T> actual;
        public IFilter<T> filter;

        public FilterObserver(FObserver<T> actual, IFilter<T> filter) {
            this.actual = actual;
            this.filter = filter;
            Logger.i("New FilterObserver FObserver"+actual);
        }

        @Override
        public void onNext(T t) {
            Logger.i("onNext FilterObserver onNext"+actual);
            actual.onNext(t);
        }

        @Override
        public void onError() {

        }

        @Override
        public void onComplete() {

        }
    }
}
