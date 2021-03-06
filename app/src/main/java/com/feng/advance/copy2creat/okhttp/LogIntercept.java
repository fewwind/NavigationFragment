package com.feng.advance.copy2creat.okhttp;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fewwind on 19-3-19.
 */

public class LogIntercept implements Intercept {
    @Override
    public Response intercept(Chain chain) {
        Request request = chain.getRequest();
        Request.Builder builder = request.newBuilder();
        builder.method(request.method() + "-->log", null);
        request = builder.build();
        Response response = chain.process(request);
        response = response.newBuilder().message(response.message() + "-->log").build();
        return response;
    }
}
