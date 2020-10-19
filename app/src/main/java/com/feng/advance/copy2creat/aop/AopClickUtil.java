package com.feng.advance.copy2creat.aop;

import android.view.View;
import com.orhanobut.logger.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 自动埋点拦截点击事件
 */
@Aspect
public class AopClickUtil {
    @Before(value = "onViewClick()")
    public void onViewClickBefore(JoinPoint joinPoint) {
        View v = (View) joinPoint.getArgs()[0];
        Logger.v("ClickId ->" + v.getContext().getResources().getResourceEntryName((v.getId())));
    }


    @After(value = "onViewClick()")
    public void onViewClickAfter(JoinPoint joinPoint) {
    }


    @Pointcut("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
    public void onViewClick() {

    }
}
