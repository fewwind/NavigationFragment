package com.chaozhuo.parentmanager.activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.fragment.FragmentFactory;
import com.chaozhuo.parentmanager.fragment.LearnListFragment;
import com.chaozhuo.parentmanager.mvvm.CheckLogin;
import com.chaozhuo.parentmanager.test.kotlin.KotlinmActivity;
import com.chaozhuo.parentmanager.weight.touch.TouchUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by fewwind on 19-1-7.
 */

public class SplashActivity extends AppCompatActivity implements LearnListFragment.IFragClick, LifecycleOwner {
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
        getSupportFragmentManager().beginTransaction().add(R.id.container, learnListFragment).commitAllowingStateLoss();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RouteDemo.getInstance().build("main").start();
//                ARouter.getInstance().build("/loginGroup/ui").navigation(SplashActivity.this);
                Fragment fragment = (Fragment) ARouter.getInstance().build("/main/view").navigation();
                Logger.v("frag" + fragment);
//                MainActivity.start(SplashActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(SplashActivity.this).show();
            }
        });

//        Intent intent = new Intent();
//        ComponentName neam = new ComponentName("com.chaozhuo.grow", "com.chaozhuo.grow.MainActivity");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//        intent.setComponent(neam);
//        startActivity(intent);
        EventBus.getDefault().register(this);
        startActivity(new Intent(this, KotlinmActivity.class));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchUtil.touch(this, event);
        return super.onTouchEvent(event);
    }

    @CheckLogin(param = "login")
    @Override
    public void click(Class type) {
        mType = type;
        getSupportFragmentManager().beginTransaction().hide(learnListFragment).add(R.id.container, FragmentFactory.creat(mType)).commitAllowingStateLoss();
    }

    @Subscribe()
    public void switchFragment(Object o) {
    }

    @Override
    public void onBackPressed() {
        if (mType == null) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().beginTransaction().remove(FragmentFactory.creat(mType)).commitAllowingStateLoss();
            getSupportFragmentManager().beginTransaction().show(learnListFragment).commitAllowingStateLoss();
            mType = null;
        }
    }

    private LifecycleRegistry mRegistry;

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mRegistry;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if ("FrameLayout".equals(name)) {
            int count = attrs.getAttributeCount();
            for (int i = 0; i < count; i++) {
                String attributeName = attrs.getAttributeName(i);
                String value = attrs.getAttributeValue(i);
                if (attributeName.equals("id")) {
                    int id = Integer.parseInt(value.substring(1));
                    Logger.v(attributeName + "<>" + getResources().getResourceName(id));
                    if ("android:id/content".equals(getResources().getResourceName(id))) {

                    }
                }
            }
        }
        return super.onCreateView(name, context, attrs);
    }
}
