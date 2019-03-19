package com.chaozhuo.parentmanager.test.okhttp;

import com.orhanobut.logger.Logger;

import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fewwind on 19-3-19.
 */

public class RealChain implements Intercept.Chain {
    public List<Intercept> intercept;
    private int index = 0;
    private Request mRequest;

    public RealChain(List<Intercept> intercept, int pos, Request request) {
        this.intercept = intercept;
        index = pos;
        mRequest = request;
    }

    @Override
    public Request getRequest() {
        return mRequest;
    }

    @Override
    public Response process(Request request) {
        RealChain next = new RealChain(intercept, index + 1, request);
        Logger.i(request.method());
        Intercept intercept = this.intercept.get(index);
        Response response = intercept.intercept(next);
        Logger.v(response.message());
        return response;
    }
}
