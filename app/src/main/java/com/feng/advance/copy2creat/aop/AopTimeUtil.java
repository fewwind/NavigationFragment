package com.feng.advance.copy2creat.aop;

import android.os.SystemClock;
import com.feng.advance.design.compentent.TraceTimeAOP;
import com.orhanobut.logger.Logger;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AopTimeUtil {
    //编译后的class在 intermeditas/transform/ajx/debug/0
    @Pointcut("execution(@com.feng.advance.design.compentent.TraceTimeAOP * *(..))")
    public void checkTime() {

    }


    @Around("checkTime()")
    public Object check(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TraceTimeAOP traceTimeAOP = method.getAnnotation(TraceTimeAOP.class);
        if (traceTimeAOP != null){
            long s = SystemClock.uptimeMillis();
            joinPoint.proceed();
            long t = (SystemClock.uptimeMillis() - s);
            if (t>300){
                Logger.e("time = "+method.getName()+">"+t);
            }
        }
        //return joinPoint.proceed();
        return null;
    }
}
