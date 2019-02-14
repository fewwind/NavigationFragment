package com.chaozhuo.parentmanager.test.okhttp;

/**
 * Created by fewwind on 18-12-28.
 */

public class NetInterceptor implements Interceptor {
    @Override
    public String intercept(Chain chain)  {
        String netInterceptor = chain.proceed("NetInterceptor");
        if (netInterceptor.contains("Error")){
            return "重新请求结果"+chain.proceed("拦截重新请求 NetInterceptor");
        }
        return "NetInterceptor = "+netInterceptor;
    }
}
