package com.chaozhuo.parentmanager.copy2creat.eventbus;

import com.orhanobut.logger.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bus {
    public static Bus bus = new Bus();
    private List<SubMethod> map = new ArrayList<>();
    private HashMap<Class, CopyOnWriteArrayList<SubMethod>> subMap = new HashMap<>();

    public void regist(Object home) {
        Method[] declaredMethods = home.getClass().getDeclaredMethods();
        for (Method me : declaredMethods) {
            if (me.isAnnotationPresent(Event.class)) {
                SubMethod method = new SubMethod();
                method.clzz = home.getClass();
                method.event = me.getParameterTypes()[0];
                method.method = me;
                method.home = home;
                Logger.v("meth = " + method);
                map.add(method);
            }
        }
        for (SubMethod sub : map) {
            if (subMap.containsKey(sub.event)) {
                subMap.get(sub.event).add(sub);
            } else {
                CopyOnWriteArrayList<SubMethod> list = new CopyOnWriteArrayList<>();
                list.add(sub);
                subMap.put(sub.event, list);
            }
        }
    }

    public void post(Object obj) {
        CopyOnWriteArrayList<SubMethod> list = subMap.get(obj.getClass());
        if (list != null) {
            for (SubMethod sm : list) {
                try {
                    sm.method.invoke(sm.home, obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        BeiGuanCha.creat(new SubObserver() {
            @Override
            public void doSub(GuanCha cha) {

            }
        }).subscribe(new GuanCha() {
            @Override
            public void onNext() {
                super.onNext();
            }
        });
    }

    interface SubObserver {
        void doSub(GuanCha cha);
    }

    private static class BeiGuanCha {
        SubObserver subObserver;

        public BeiGuanCha(SubObserver subObserver) {
            this.subObserver = subObserver;
        }

        public static BeiGuanCha creat(SubObserver subObserver) {
            return new BeiGuanCha(subObserver);
        }

        public void subscribe(GuanCha guanCha) {
            subObserver.doSub(guanCha);
        }
    }

    private class GuanCha {
        public void onNext() {

        }
    }
}
