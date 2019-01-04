package com.chaozhuo.parentmanager.test.okhttp;

import com.orhanobut.logger.Logger;

import java.io.IOException;

/**
 * Created by fewwind on 18-12-28.
 */

public class NetInterceptor implements Interceptor {
    @Override
    public String intercept(Chain chain) throws IOException {
        Logger.v("NetInterceptor ");
        String netInterceptor = chain.proceed("NetInterceptor");
        return "NetInterceptor = "+netInterceptor;
    }
}
