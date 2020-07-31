package com.feng.advance.copy2creat.aop;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.feng.advance.design.compentent.PermissionAOP;
import com.feng.advance.util.permission.IPermissionListenerWrap;
import com.feng.advance.util.permission.PermissionsHelper;
import com.orhanobut.logger.Logger;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AopUtil {
    //编译后的class在 intermeditas/transform/ajx/debug/0
    @Pointcut("execution(@com.feng.advance.design.compentent.PermissionAOP * *(..))")
    public void checkPermission() {

    }


    @Around("checkPermission()")
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        PermissionAOP check = method.getAnnotation(PermissionAOP.class);
        if (check != null) {
            FragmentActivity context = null;
            if (joinPoint.getTarget() instanceof Fragment) {
                context = ((Fragment) joinPoint.getTarget()).getActivity();
            } else {
                context = (FragmentActivity) joinPoint.getTarget();
            }
            Logger.i((joinPoint.getThis() instanceof Activity) + " <> " + joinPoint.getTarget() + "\n" +
                    Arrays.toString(check.permissions()));
            if (context != null) {
                String[] permissions = check.permissions();
                PermissionsHelper.init(context).requestPermissions(permissions,
                        new IPermissionListenerWrap.IPermissionListener() {
                            @Override public void onAccepted(boolean isGranted) {
                                if (isGranted) {
                                    try {
                                        joinPoint.proceed();
                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                }
                            }


                            @Override public void onException(Throwable throwable) {

                            }
                        });
            }
        }

        //return joinPoint.proceed();
        return null;
    }
}
