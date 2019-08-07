package com.chaozhuo.parentmanager.test;

import android.content.Context;

import com.chaozhuo.parentmanager.mvvm.CheckLogin;
import com.orhanobut.logger.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by fewwind on 19-6-24.
 * 用例：
 * 1，运行时权限，在需要权限的方法上加注解，
 */

@Aspect
public class CheckLoginAspectJ {

    @Pointcut("execution(@com.chaozhuo.parentmanager.mvvm.CheckLogin * *(..))")
    public void pointCut() {
        // 这种拦截被注解的方法
    }
//    @Pointcut("call(* com.chaozhuo.parentmanager.activity.SplashActivity.*(..))")
//    public void pointCut() {
//      可以拦截此类所有方法
//    }

    @Before("pointCut()")
    public void before(JoinPoint point) {
        //aspectj class bianyi java.classw
        Logger.i("CheckLoginAspectJ.before");
    }

    @Around("pointCut()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger.i("checkLogin");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CheckLogin checkLogin = method.getAnnotation(CheckLogin.class);
        String content = checkLogin.param();

        Context context = null;
        boolean isLogin = false;

        for (Object obj : joinPoint.getArgs()) {
            if (obj instanceof Context) {
                context = (Context) obj;
            }

            if (obj instanceof Boolean) {
                isLogin = (boolean) obj;
            }
            Logger.w("pointArg = " + obj);
        }
        return joinPoint.proceed();
    }

    @After("pointCut()")
    public void after(JoinPoint point) {
        Logger.w("CheckLoginAspectJ.after");
    }

}
