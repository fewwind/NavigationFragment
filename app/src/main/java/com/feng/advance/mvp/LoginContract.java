package com.feng.advance.mvp;

import com.feng.advance.mvp.base.IBaseModel;
import com.feng.advance.mvp.base.IBaseView;

/**
 * Created by fewwind on 18-12-24.
 */

public class LoginContract {
    interface Model extends IBaseModel{
        void goLogin();
    }
    interface View extends IBaseView{
        void loginSuccess();
    }
    interface Presenter{
        void login();
    }
}
