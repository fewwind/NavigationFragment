package com.chaozhuo.parentmanager.mvp.base;

import android.content.Context;

/**
 * Created by fewwind on 18-12-24.
 */

public interface IBaseView {
    void showLoaind();
    void dismissLoad();
    void onEmpty();
    void onError();
    void onSuccess();
    Context getContext();
}
