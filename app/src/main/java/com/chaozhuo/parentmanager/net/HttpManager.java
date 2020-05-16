package com.chaozhuo.parentmanager.net;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.chaozhuo.parentmanager.BuildConfig;
import com.chaozhuo.parentmanager.bean.OnLineConfigBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
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
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request.Builder build = chain.request()
                        .newBuilder()
                        .addHeader("Cookie", "")
                        //这个代表接受json参数
                        .addHeader("Accept", "application/json");
                Request request = build.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("base")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mHttpService = mRetrofit.create(HttpService.class);
    }

    private Interceptor getAddPIntercepter() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                RequestBody body = request.body();// 得到请求体
                Buffer buffer = new Buffer();// 创建缓存
                body.writeTo(buffer);//将请求体内容,写入缓存
                String parameterStr = buffer.readUtf8();// 读取参数字符串
                //如果是json串就解析 从新加餐 如果是字符串就进行修改 具体业务逻辑自己加
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(parameterStr);
                    jsonObject.put("from", "mpc_car_and");
                    jsonObject.put("taskId", UUID.randomUUID().toString());
//                    jsonObject.put("clientVersion", "1.32");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //对应请求头大伙按照自己的传输方式 定义
                RequestBody requestBody = RequestBody.create(MEDIA_TYPE, jsonObject == null ? parameterStr : jsonObject.toString());
                request = request.newBuilder().patch(requestBody).build();

                Response response = chain.proceed(request);
                return response;
            }
        };
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

    public <T> T toBean(String json, Type t) {
        if (t == null) return null;
        try {
            T obj = new Gson().fromJson(json, t);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // typeToken是万能的List<OnLineConfigBean>可以换成任意
    public void toList() {
        List<OnLineConfigBean> list = new Gson().fromJson("", new TypeToken<List<OnLineConfigBean>>() {
        }.getType());
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

    private <T> Class<T> getClass(CZCallBack<T> callBack) {
        return (Class<T>) ((ParameterizedType) callBack.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract static class CZCallBack<T> {
        public abstract void onSuccess(T t);

        public abstract void onFail(String msg);
    }

    String douban = "https://api.douban.com/v2/movie/imdb/tt0111161?apikey=0df993c66c0c636e29ecbb5344252a4a";

    public void cacheMethod() {
        CacheControl control = new CacheControl.Builder().maxAge(20, TimeUnit.SECONDS).build();
        Cache cache = new Cache(new File(""), 1024 * 1024 * 6);
        OkHttpClient client = new OkHttpClient.Builder().cache(cache).addNetworkInterceptor(new NetCacheInterceptor()).build();
        //.cacheControl等价于设置header，二者选其一
        client.newCall(new Request.Builder().url(douban).cacheControl(control).header("Cache-Control", "max-age=60").build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.w("--" + response.body().string());
            }
        });
    }

    public class NetCacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response originResponse = chain.proceed(request);

            if (!TextUtils.isEmpty(request.header("Cache-Control"))) {
                originResponse = originResponse.newBuilder()
                        .removeHeader("pragma")
//                        .header("Cache-Control", "max-age=60")
                        //让每个header决定缓存时间
                        .header("Cache-Control", request.header("Cache-Control"))
                        .build();
            }

            return originResponse;
        }
    }
}
