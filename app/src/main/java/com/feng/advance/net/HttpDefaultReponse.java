package com.feng.advance.net;

import com.feng.advance.base.BaseResponse;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class HttpDefaultReponse<T> implements Observer<BaseResponse<T>> {
    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (response.errno == 0) {
            if (response.data == null) {
                Class typeArgument = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                try {
                    response.data = (T) typeArgument.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            onSuccess(response.data);
        } else {
            filterCode(response.errmsg, response.errno);
        }
    }

    @Override
    public void onError(Throwable e) {
        String msg = "未知错误";
        if (e instanceof UnknownHostException) {
            msg = "网络错误";
        } else if (e instanceof JSONException || e instanceof JsonParseException) {

        } else if (e instanceof SocketTimeoutException) {

        } else if (e instanceof ConnectException) {

        }
        onError(msg);
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable(d);
    }

    void disposable(Disposable d) {
    }

    private void filterCode(String msg, int code) {
        onError(msg);
    }

    public abstract void onSuccess(T t);

    public abstract void onError(String msg);
}
