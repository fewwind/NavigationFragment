package com.chaozhuo.login;

import com.chaozhuo.common.BaseApp;
import com.orhanobut.logger.Logger;

public class LoginApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.w("LoginApp");
    }
}
