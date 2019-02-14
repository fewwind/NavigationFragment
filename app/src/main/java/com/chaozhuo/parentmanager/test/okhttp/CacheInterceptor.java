package com.chaozhuo.parentmanager.test.okhttp;

/**
 * Created by fewwind on 18-12-28.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public String intercept(Chain chain) {
        String cacheInterceptor = chain.proceed("CacheInterceptor");
        return "CacheInterceptor = "+cacheInterceptor;
    }
}
