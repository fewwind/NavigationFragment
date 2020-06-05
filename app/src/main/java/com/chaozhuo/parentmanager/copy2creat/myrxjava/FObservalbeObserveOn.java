package com.chaozhuo.parentmanager.copy2creat.myrxjava;

import com.orhanobut.logger.Logger;

import io.reactivex.Scheduler;

/**
 * Created by fewwind on 18-12-27.
 */

public class FObservalbeObserveOn<T> extends FObservable<T> {
    private FObservable<T> source;
    Scheduler mScheduler;

    public FObservalbeObserveOn(FObservable<T> source, Scheduler scheduler) {
        this.source = source;
        mScheduler = scheduler;
        Logger.v("New FObservalbeObserveOn = " + source);
    }

    @Override
    protected void subscribeActual(FObserver<T> observer) {
        Logger.v("FObservalbeObserveOn = subscribeActual-->" + observer);
        source.subscribe(new ObserveOnObserver<T>(observer, mScheduler.createWorker()));
    }

    class ObserveOnObserver<T> implements FObserver<T> ,Runnable{
        FObserver<T> actual;
        Scheduler.Worker worker;
        T next;
        public ObserveOnObserver(FObserver<T> actual, Scheduler.Worker worker) {
            this.actual = actual;
            this.worker = worker;
        }

        @Override
        public void onNext(T t) {
            next = t;
            worker.schedule(this);
        }

        @Override
        public void onError() {

        }

        @Override
        public void onComplete() {

        }


        @Override public void run() {
            Logger.v("ObserveOnObserver Worker Thread == > "+actual);
            actual.onNext(next);
        }
    }
}
