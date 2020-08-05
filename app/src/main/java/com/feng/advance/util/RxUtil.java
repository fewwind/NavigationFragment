package com.feng.advance.util;

import com.orhanobut.logger.Logger;
import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {
    public static <T> ObservableTransformer<T, T> schedule() {
        return (ObservableTransformer) upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).doOnError((Consumer<Throwable>) throwable -> {
            Logger.e("error = "+throwable);
        });
    }

    public static <T> SingleTransformer<T, T> scheduleS() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnError(throwable -> Logger
                .e("error = "+throwable));
    }

    public static <T> FlowableTransformer<T, T> scheduleF() {
        return (FlowableTransformer) upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).doOnError(throwable -> Logger.e("error = "+throwable));
    }

}
