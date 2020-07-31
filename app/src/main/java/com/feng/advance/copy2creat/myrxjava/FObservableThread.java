package com.feng.advance.copy2creat.myrxjava;

import android.os.Handler;
import android.os.Looper;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-3-7.
 */

public class FObservableThread<T> extends FObservable<T> {
    public FObservableSource<T> source;
    public Fwork worker;

    public FObservableThread(FObservableSource<T> source) {
        this.source = source;
        worker = new Fwork();
        Logger.i("new FObservableFilter " + source);
    }

    @Override
    protected void subscribeActual(FObserver<T> observer) {
        Logger.i("FObservableFilter subscribeActual" + observer);
        source.subscribe(new ThreadObserver<T>(observer));
    }

    class ThreadObserver<T> implements FObserver<T>, Runnable {
        public FObserver<T> actual;
        public T t;

        public ThreadObserver(FObserver<T> actual) {
            this.actual = actual;
            Logger.i("New FilterObserver FObserver" + actual);
        }

        @Override
        public void onNext(T t) {
            this.t = t;
            Logger.i("onNext FilterObserver onNext" + actual);
            worker.schedule(this);
        }

        @Override
        public void onError() {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void run() {
            // 下游的observer实际执行在当前observer的run方法，而run方法经过worker后改变线程，demo只是简化为主线程，
            // 实际是worker.schedule（runnable）提交给线程池管理
            actual.onNext(t);
        }
    }

    static class Fwork {
        public static Handler mainHandler = new Handler(Looper.getMainLooper());

        public void schedule(Runnable runnable) {
            mainHandler.post(runnable);
        }
    }
}
