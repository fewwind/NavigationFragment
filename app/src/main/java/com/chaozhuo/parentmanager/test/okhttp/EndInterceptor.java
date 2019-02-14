package com.chaozhuo.parentmanager.test.okhttp;

import java.util.Random;

/**
 * Created by fewwind on 18-12-28.
 */

public class EndInterceptor implements Interceptor {
    @Override
    public String intercept(Chain chain) {
        if (new Random().nextInt(10) % 2 == 0) {
            return "EndInterceptor Error";
        } else {
            return "EndInterceptor";
        }
    }
}
