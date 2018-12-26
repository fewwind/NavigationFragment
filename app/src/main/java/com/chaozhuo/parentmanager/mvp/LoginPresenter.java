package com.chaozhuo.parentmanager.mvp;

import com.chaozhuo.parentmanager.mvp.base.BasePresenter;

/**
 * Created by fewwind on 18-12-24.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

    @Override
    public void login() {
        if (isViewAlive()){
            getView().showLoaind();
            getModel().goLogin();
        }
    }

    @Override
    protected LoginModel creatModel() {
        return new LoginModel();
    }

}
