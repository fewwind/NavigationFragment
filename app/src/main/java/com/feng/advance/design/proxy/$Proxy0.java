package com.feng.advance.design.proxy;

import com.feng.advance.test.simpleplugin.ILoadBean;
import com.orhanobut.logger.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class $Proxy0 extends Proxy implements ILoadBean {
    public $Proxy0(InvocationHandler h) {
        super(h);
    }

    private static Method method;

    @Override
    public String getLoginInfo() {
        try {
            return (String) super.h.invoke(this, method, new Object[]{});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "exception";
    }

    static {
        try {
            method = Class.forName("com.feng.advance.test.simpleplugin.ILoadBean").getMethod("getLoginInfo");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        final ILoadBean loadBean = new ILoadBean() {
            @Override
            public String getLoginInfo() {
                return "realValue";
            }
        };
        $Proxy0 proxy0 = new $Proxy0(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Object result = method.invoke(loadBean, objects);
                return "Proxy >> " + result;
            }
        });
        String loginInfo = proxy0.getLoginInfo();
        Logger.w("Proxy = " + loginInfo);
    }
}
