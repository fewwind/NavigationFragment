package com.chaozhuo.parentmanager.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.bean.OnLineConfigBean;
import com.chaozhuo.parentmanager.mvvm.ConfigViewModel;
import com.chaozhuo.parentmanager.mvvm.MyObserver;
import com.orhanobut.logger.Logger;

public class MvvmFragment extends android.support.v4.app.Fragment {

    public MvvmFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        MvvmFragment fragment = new MvvmFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    protected void initViewsAndEvents(View view) {
        View cview = view.findViewById(R.id.view_c);
        cview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e("viewC Click");
            }
        });

        getLifecycle().addObserver(new MyObserver());
        ConfigViewModel viewModel = ViewModelProviders.of(this).get(ConfigViewModel.class);
        viewModel.getConfig().observe(this, new Observer<OnLineConfigBean>() {
            @Override
            public void onChanged(@Nullable OnLineConfigBean onLineConfigBean) {

            }
        });
    }


}
