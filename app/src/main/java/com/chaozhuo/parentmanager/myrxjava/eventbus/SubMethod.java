package com.chaozhuo.parentmanager.myrxjava.eventbus;

import java.lang.reflect.Method;

public class SubMethod {
    public Class clzz;
    public Method method;
    public Class event;
    public Object home;

    @Override
    public String toString() {
        return "SubMethod{" +
                "clzz=" + clzz +
                ", method=" + method +
                ", event=" + event +
                ", home=" + home +
                '}';
    }
}
