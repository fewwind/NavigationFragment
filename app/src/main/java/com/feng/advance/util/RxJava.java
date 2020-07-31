package com.feng.advance.util;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * Created by fewwind on 18-12-13.
 */

public class RxJava {
    private RxJava() {

    }

    public static RxJava get() {
        return SingleRx.rx;
    }

    private static class SingleRx {
        private static final RxJava rx = new RxJava();
    }

    public void creatOp() {
        Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                // 反射单一数据
                emitter.onSuccess(0);// 和error不共存
            }
        }).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Exception {

            }
        });
    }
}
