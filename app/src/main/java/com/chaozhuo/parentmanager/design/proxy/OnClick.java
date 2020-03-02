package com.chaozhuo.parentmanager.design.proxy;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerSetter = "setOnClickListener",listenType = View.OnClickListener.class,method = "onClick")
public @interface OnClick {
    int[] ids() default -1;
}
