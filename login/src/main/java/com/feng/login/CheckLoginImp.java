package com.feng.login;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.feng.common.ICheckLogin;

@Route(path = "/login/check")
public class CheckLoginImp implements ICheckLogin {
    @Override public boolean isLogin() {
        return false;
    }


    @Override public void init(Context context) {

    }
}
