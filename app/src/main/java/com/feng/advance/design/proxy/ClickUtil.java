package com.feng.advance.design.proxy;

import android.app.Activity;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ClickUtil {
    public static void inject(final Activity activity) {
        if (activity == null) return;
        Class<? extends Activity> activityClass = activity.getClass();
        Method[] declaredMethods = activityClass.getDeclaredMethods();
        for (final Method method : declaredMethods) {
            OnClick annotaOnclick = method.getAnnotation(OnClick.class);
            if (annotaOnclick != null) {
                Logger.w("onclick " + annotaOnclick);
                /*********注意这里获取注解的注解*********/
                Class<? extends Annotation> eventClass = annotaOnclick.annotationType();
                EventBase annotation = eventClass.getAnnotation(EventBase.class);
                Logger.i("eventBase " + annotation);
                String setter = annotation.listenerSetter();
                Class<?> listenType = annotation.listenType();
                final String methodClick = annotation.method();
                int[] ids = annotaOnclick.ids();
                for (int id : ids) {
                    View view = activity.findViewById(id);
                    try {
                        Method setClick = view.getClass().getMethod(setter, listenType);
                        setClick.invoke(view, Proxy.newProxyInstance(listenType.getClassLoader(), new Class[]{listenType}, new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method methodIn, Object[] args) throws Throwable {
                                Logger.v(methodIn + "invoke " + method + "\n" + args[0]);
                                method.setAccessible(true);
                                return method.invoke(activity, args);
                            }
                        }));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
