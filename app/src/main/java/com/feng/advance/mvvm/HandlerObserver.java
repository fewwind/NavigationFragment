package com.feng.advance.mvvm;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Handler;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-3-13.
 */

public class HandlerObserver implements LifecycleObserver {

    private Handler mHandler;

    public HandlerObserver(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onresume() {
        Logger.v("Onresume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Logger.w("onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        if (mHandler != null) mHandler.removeCallbacksAndMessages(null);
    }
}
