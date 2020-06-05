package com.chaozhuo.parentmanager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.chaozhuo.parentmanager.copy2creat.okhttp.CacheIntercept;
import com.chaozhuo.parentmanager.copy2creat.okhttp.Intercept;
import com.chaozhuo.parentmanager.copy2creat.okhttp.LogIntercept;
import com.chaozhuo.parentmanager.copy2creat.okhttp.NetIntercept;
import com.chaozhuo.parentmanager.copy2creat.okhttp.RealChain;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Request;

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
//        realChain.process(request);

    }


}
