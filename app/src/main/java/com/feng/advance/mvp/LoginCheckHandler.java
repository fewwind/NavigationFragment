package com.feng.advance.mvp;

import com.feng.advance.test.simpleplugin.ILoadBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by fewwind on 19-6-21.
 */

public class LoginCheckHandler implements InvocationHandler {

    Object source;

    public LoginCheckHandler(Object s) {
        this.source = s;
    }

    private static <S, T extends S> T proxy(S source, Class<T> tClass) {
        return (T) Proxy.newProxyInstance(source.getClass().getClassLoader(), new Class[]{tClass}, new LoginCheckHandler(source));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!isLogin()) {
            return null;
        }
        return method.invoke(source, args);
    }

    private boolean isLogin() {
        ILoadBean bean = new ILoadBean() {
            @Override
            public String getLoginInfo() {
                return null;
            }
        };
        LoginCheckHandler.proxy(bean, ILoadBean.class);
        return false;
    }
}
