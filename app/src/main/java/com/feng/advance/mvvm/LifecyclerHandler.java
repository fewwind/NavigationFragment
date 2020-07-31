package com.feng.advance.mvvm;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Handler;


public class LifecyclerHandler extends Handler implements LifecycleObserver {
    private LifecycleOwner mOwner;

    public LifecyclerHandler(LifecycleOwner mOwner) {
        this.mOwner = mOwner;
        addObserver();
    }

    private void addObserver() {
        mOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        removeCallbacksAndMessages(null);
        mOwner.getLifecycle().removeObserver(this);
    }

}
