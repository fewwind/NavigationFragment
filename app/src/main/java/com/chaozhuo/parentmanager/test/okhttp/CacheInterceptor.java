package com.chaozhuo.parentmanager.test.okhttp;

import com.orhanobut.logger.Logger;

import java.io.IOException;

/**
 * Created by fewwind on 18-12-28.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public String intercept(Chain chain) throws IOException {
        Logger.d("CacheInterceptor ");
        String cacheInterceptor = chain.proceed("CacheInterceptor");
        return "CacheInterceptor = "+cacheInterceptor;
    }
}
