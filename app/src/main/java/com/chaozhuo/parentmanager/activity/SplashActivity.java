package com.chaozhuo.parentmanager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.chaozhuo.parentmanager.MainActivity;
import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.fragment.FragmentFactory;
import com.chaozhuo.parentmanager.fragment.LearnListFragment;
import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-1-7.
 */

public class SplashActivity extends Activity implements LearnListFragment.IFragClick {
    LearnListFragment learnListFragment;
    Class mType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        learnListFragment = new LearnListFragment();
        getFragmentManager().beginTransaction().add(R.id.container, learnListFragment).commitAllowingStateLoss();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(SplashActivity.this);
            }
        });
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

    @Override
    public void click(Class type) {
        mType = type;
        getFragmentManager().beginTransaction().hide(learnListFragment).add(R.id.container, FragmentFactory.creat(mType)).commitAllowingStateLoss();
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
}
