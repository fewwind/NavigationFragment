package com.chaozhuo.parentmanager.copy2creat.okhttp;

import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fewwind on 19-3-19.
 */

public class NetIntercept implements Intercept {
    @Override
    public Response intercept(Chain chain) {
        Request request = chain.getRequest();
        Request.Builder builder = request.newBuilder();
        builder.method(request.method() + "-->net", null);
        request = builder.build();
        Response response = new Response.Builder().code(200).request(request).protocol(Protocol.HTTP_1_1).message("NetResponse").build();
        return response;
    }
}
