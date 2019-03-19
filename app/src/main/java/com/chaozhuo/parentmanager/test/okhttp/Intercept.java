package com.chaozhuo.parentmanager.test.okhttp;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fewwind on 19-3-19.
 */

public interface Intercept {

    Response intercept(Chain chain);

    public interface Chain {
        Request getRequest();

        Response process(Request request);
    }
}
