package com.chaozhuo.parentmanager.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.chaozhuo.parentmanager.bean.OnLineConfigBean;
import com.chaozhuo.parentmanager.mvvm.ConfigViewModel;
import com.chaozhuo.parentmanager.mvvm.HandlerObserver;
import com.chaozhuo.parentmanager.util.Event.BaseEvent;
import com.chaozhuo.parentmanager.util.Event.EventObserver;
import com.chaozhuo.parentmanager.util.Event.IEventListener;
import com.chaozhuo.parentmanager.util.Event.livedata.EventLiveData;
import com.chaozhuo.parentmanager.util.Event.livedata.LiveObserver;
import com.orhanobut.logger.Logger;

public class MvvmFragment extends BaseFragment {

    public MvvmFragment() {
        // Required empty public constructor
    }

    private Handler mHandler;

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        MvvmFragment fragment = new MvvmFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    protected void initViewsAndEvents(View view) {
        View cview = view.findViewById(R.id.view_c);
        cview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("viewC Click");
            }
        });
        mHandler = new Handler();
        getLifecycle().addObserver(new HandlerObserver(mHandler));
        ConfigViewModel viewModel = ViewModelProviders.of(getActivity()).get(ConfigViewModel.class);
        viewModel.getConfig().observe(this, new Observer<OnLineConfigBean>() {
            @Override
            public void onChanged(@Nullable OnLineConfigBean onLineConfigBean) {
                Logger.w("One - " + onLineConfigBean.name);
            }
        });
        viewModel.getConfig().observe(this, new Observer<OnLineConfigBean>() {
            @Override
            public void onChanged(@Nullable OnLineConfigBean onLineConfigBean) {
                Logger.w("two - " + onLineConfigBean.name);
            }
        });

        EventLiveData.DATA.observe(this, new LiveObserver<String>("add") {
            @Override
            public void dataChange(String event) {
                Logger.w("key= " + event);
            }
        });
        EventLiveData.DATA.setValue(new BaseEvent("add", ""));
        getLifecycle().addObserver(new EventObserver("add", new IEventListener() {
            @Override
            public void onEventListener(BaseEvent event) {

            }
        }));
    }


}
