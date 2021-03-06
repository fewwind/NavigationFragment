package com.feng.advance.net;

import com.feng.advance.bean.AppInfoBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by fewwind on 18-2-26.
 */

public interface HttpService {
    @GET("")
    Call<AppInfoBean> getData(String s);

    @GET("")
    Observable<AppInfoBean> getDataRx(String s);
}
