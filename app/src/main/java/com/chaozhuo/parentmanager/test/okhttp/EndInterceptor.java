package com.chaozhuo.parentmanager.test.okhttp;

import com.orhanobut.logger.Logger;

import java.io.IOException;

/**
 * Created by fewwind on 18-12-28.
 */

public class EndInterceptor implements Interceptor {
    @Override
    public String intercept(Chain chain) throws IOException {
        Logger.e("EndInterceptor ");
        return "EndInterceptor";
    }
}
