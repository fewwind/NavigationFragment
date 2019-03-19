package com.chaozhuo.parentmanager.net;

import android.os.Handler;
import android.os.Looper;

import com.chaozhuo.parentmanager.BuildConfig;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fewwind on 18-2-26.
 */

public class HttpManager {

    private static int TIME_OUT = 20;
    private Retrofit mRetrofit;
    private HttpService mHttpService;

    private HttpManager() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //builder.addInterceptor(loggingInterceptor);
        }
        Handler handler = new Handler();
        Looper.prepare();
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("base")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mHttpService = mRetrofit.create(HttpService.class);
    }

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private static class SingleInstance {
        public static final HttpManager instance = new HttpManager();
    }

    public static HttpManager get() {
        return SingleInstance.instance;
    }

    public HttpService getService() {
        return mHttpService;
    }

    public static <T> T creatApi(Class<T> clazz) {
        return get().mRetrofit.create(clazz);
    }

    /**
     * 泛型封装
     *
     * @param callBack
     * @param <T>
     * @return
     */
    private <T> Type getTypeT(CZCallBack<T> callBack) {
        new Gson().fromJson("", getTypeT(callBack));//用法
        return ((ParameterizedType) callBack.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract static class CZCallBack<T> {
        public abstract void onSuccess(T t);

        public abstract void onFail(String msg);
    }
}
