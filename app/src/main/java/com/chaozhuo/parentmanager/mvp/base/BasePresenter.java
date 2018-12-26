package com.chaozhuo.parentmanager.mvp.base;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by fewwind on 18-12-24.
 */

public abstract class BasePresenter<V extends IBaseView, M extends IBaseModel> {
    protected V mView;
    protected M mModel;
    protected WeakReference<V> weakReference;

    public BasePresenter(V view) {
        attchView(view);
    }

    public BasePresenter() {
    }

    private void attchView(V view) {
        weakReference = new WeakReference<V>(view);
        mView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (isViewAlive()) {
                    return method.invoke(args);
                }
                return null;
            }
        });

        if (mModel == null) {
            mModel = creatModel();
        }
    }

    protected abstract M creatModel();

    protected M getModel() {
        return mModel;
    }

    protected V getView() {
        return mView;
    }

    protected void showLoading(){
        getView().showLoaind();
    }
    protected void dismissLoading(){
        getView().dismissLoad();
    }

    public void detachView() {
        this.mModel = null;
        if (isViewAlive()) {
            weakReference.clear();
            weakReference = null;
        }
    }

    protected boolean isViewAlive() {
        return weakReference != null && weakReference.get() != null;
    }
}
