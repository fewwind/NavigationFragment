package com.feng.advance.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.feng.advance.R;
import com.feng.advance.base.BaseFragment;
import com.feng.advance.bean.OnLineConfigBean;
import com.feng.advance.mvvm.ConfigViewModel;
import com.feng.advance.mvvm.HandlerObserver;
import com.feng.advance.copy2creat.Event.BaseEvent;
import com.feng.advance.copy2creat.Event.EventObserver;
import com.feng.advance.copy2creat.Event.IEventListener;
import com.feng.advance.copy2creat.Event.livedata.EventLiveData;
import com.feng.advance.copy2creat.Event.livedata.LiveObserver;
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
