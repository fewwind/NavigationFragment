package com.feng.common;

import android.support.v4.app.Fragment;

public class BaseComponent {
    private ILoginModel mLoginModel;

    private static class Single {
        private static BaseComponent component = new BaseComponent();
    }

    public static BaseComponent get() {
        return Single.component;
    }

    public void setLoginModel(ILoginModel loginModel) {
        mLoginModel = loginModel;
    }

    public ILoginModel getLoginModel() {
        if (mLoginModel == null) {
            mLoginModel = new ILoginModel() {
                @Override
                public boolean isLogin() {
                    return false;
                }

                @Override
                public Fragment getUserFragment() {
                    return null;
                }
            };
        }
        return mLoginModel;
    }
}
