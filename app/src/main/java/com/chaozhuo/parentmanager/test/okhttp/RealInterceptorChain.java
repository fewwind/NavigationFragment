package com.chaozhuo.parentmanager.test.okhttp;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fewwind on 18-12-28.
 */

public class RealInterceptorChain implements Interceptor.Chain {

    private int index = 0;
    private ArrayList<Interceptor> interceptors;

    @Override
    public String request() {
        return null;
    }

    public RealInterceptorChain(int dex) {
        this.index = dex;
        if (interceptors == null) interceptors = new ArrayList<>();
        if (interceptors.size() != 0) return;
        interceptors.add(new CacheInterceptor());
        interceptors.add(new NetInterceptor());
        interceptors.add(new EndInterceptor());
    }

    @Override
    public String proceed(String request) throws IOException {
        if (index >= interceptors.size()) {
            throw new NullPointerException("");
        }
        RealInterceptorChain next = new RealInterceptorChain(index + 1);
        Interceptor interceptor = interceptors.get(index);
        String response = interceptor.intercept(next);
        Logger.w("real = "+response);
        return response;
    }
}
