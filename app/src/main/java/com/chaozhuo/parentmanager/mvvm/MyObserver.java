package com.chaozhuo.parentmanager.mvvm;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-3-13.
 */

public class MyObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onresume() {
        Logger.v("Onresume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Logger.w("onPause");
    }
}
