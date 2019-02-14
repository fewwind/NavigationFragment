package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.chaozhuo.parentmanager.test.okhttp.RealInterceptorChain;
import com.orhanobut.logger.Logger;

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
        RealInterceptorChain chain = new RealInterceptorChain(0);
        chain.proceed("request");
    }


}
