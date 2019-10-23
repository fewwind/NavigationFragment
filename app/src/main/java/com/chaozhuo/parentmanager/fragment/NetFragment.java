package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.chaozhuo.parentmanager.test.okhttp.CacheIntercept;
import com.chaozhuo.parentmanager.test.okhttp.Intercept;
import com.chaozhuo.parentmanager.test.okhttp.LogIntercept;
import com.chaozhuo.parentmanager.test.okhttp.NetIntercept;
import com.chaozhuo.parentmanager.test.okhttp.RealChain;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetFragment extends BaseFragment {

    public NetFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        NetFragment fragment = new NetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_touch_event;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        View cview = view.findViewById(R.id.view_c);
        cview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("viewC Click");
            }
        });

        List<Intercept> list = new ArrayList<>();
        list.add(new CacheIntercept());
        list.add(new LogIntercept());
        list.add(new NetIntercept());
        Request request = new Request.Builder().url("http://www.qq.com").method("start", null).build();
        RealChain realChain = new RealChain(list, 0, request);
        realChain.process(request);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        }).build();
        Message.obtain();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


}
