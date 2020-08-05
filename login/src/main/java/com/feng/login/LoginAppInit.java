package com.feng.login;

import com.feng.rounte_annotation.AppStart;
import com.feng.rounte_annotation.IAppInit;
import com.orhanobut.logger.Logger;

@AppStart(sort = 3)
public class LoginAppInit implements IAppInit {
    @Override public void init() {
        Logger.v(getClass().getSimpleName() + "init");
    }
}
