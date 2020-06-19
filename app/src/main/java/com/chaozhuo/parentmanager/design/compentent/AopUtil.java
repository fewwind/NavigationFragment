package com.chaozhuo.parentmanager.design.compentent;

import android.content.Context;
import android.support.v4.app.Fragment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopUtil {

    @Pointcut("execution(@com.chaozhuo.parentmanager.design.compentent.PermissionAOP * *(..) && @annotation(permission))")
    public void checkPermission(PermissionAOP permission){

    }
    public Object check(ProceedingJoinPoint joinPoint,PermissionAOP check) throws Throwable{
        if (check != null){
            Context context = null;
            if (joinPoint.getThis() instanceof Fragment){
                context = ((Fragment) joinPoint.getThis()).getActivity();
            } else {
                context = ((Context) joinPoint.getThis());
            }
            if (context != null){
                String[] permissions = check.permissions();
            }
        }

        return joinPoint.proceed();
    }
}
