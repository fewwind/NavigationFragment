package com.chaozhuo.parentmanager.activity;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.fragment.FragmentFactory;
import com.chaozhuo.parentmanager.fragment.LearnListFragment;
import com.chaozhuo.parentmanager.mvvm.CheckLogin;
import com.chaozhuo.parentmanager.test.KotlinTest;
import com.chaozhuo.route_api.RouteDemo;
import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-1-7.
 */

public class SplashActivity extends Activity implements LearnListFragment.IFragClick, LifecycleOwner {
    LearnListFragment learnListFragment;
    Class mType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 如果不是Fragment或者支持库需要自己实现
        mRegistry = new LifecycleRegistry(this);
        mRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);

        learnListFragment = new LearnListFragment();
        getFragmentManager().beginTransaction().add(R.id.container, learnListFragment).commitAllowingStateLoss();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RouteDemo.getInstance().build("main").start();
//                MainActivity.start(SplashActivity.this);
            }
        });

        String s = ""+(Integer.MAX_VALUE+1000);
        Logger.e("S = "+Integer.valueOf(s));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.e("Activity Touch = " + event);
        return super.onTouchEvent(event);
    }

    @CheckLogin(param = "login")
    @Override
    public void click(Class type) {
        mType = type;
        getFragmentManager().beginTransaction().hide(learnListFragment).add(R.id.container, FragmentFactory.creat(mType)).commitAllowingStateLoss();
        switchFragment();
        KotlinTest test = new KotlinTest("");
        test.getSName();
    }

    private void switchFragment() {
    }

    @Override
    public void onBackPressed() {
        if (mType == null) {
            super.onBackPressed();
        } else {
            getFragmentManager().beginTransaction().remove(FragmentFactory.creat(mType)).commitAllowingStateLoss();
            getFragmentManager().beginTransaction().show(learnListFragment).commitAllowingStateLoss();
            mType = null;
        }
    }

    private LifecycleRegistry mRegistry;

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mRegistry;
    }
}
