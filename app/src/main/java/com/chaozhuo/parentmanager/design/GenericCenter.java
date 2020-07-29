package com.chaozhuo.parentmanager.design;

import com.orhanobut.logger.Logger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericCenter {
    //https://www.cnblogs.com/doucheyard/p/6855823.html
    public static void show() {
        Field field = Sellers.class.getFields()[0];
        //java.util.List<com.chaozhuo.parentmanager.design.Apple>
        Type genericType = field.getGenericType();
        Type actualType = null;
        if (genericType instanceof ParameterizedType) {
            //com.chaozhuo.parentmanager.design.Apple
            actualType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }
        try {
            Method method = Sellers.class.getMethod("getApples", new Class[] { List.class });
            //java.util.List<com.chaozhuo.parentmanager.design.Apple>
            ParameterizedType methodType = (ParameterizedType) method.getGenericParameterTypes()[0];
            // 实际类型 class com.chaozhuo.parentmanager.design.Apple
            Logger.w(methodType + "<>" + methodType.getActualTypeArguments()[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}


class Fruit {
    public void showColor() {

    }
}


class Apple extends Fruit {

}


class Sellers<T extends Fruit> {
    public List<Apple> mApples;


    // 方法返回值明确泛型类型，反射能获取实际类型
    public List<Apple> getApples(List<Apple> apples) {
        return mApples;
    }


    public void showColor(T t) {
        t.showColor();
    }


    public List<T> getList(List<T> product) {
        return null;
    }


    // 静态方法无法引用当前class的泛型，她的泛型是单独的
    public static <T> List<T> getListStatic(List<T> product) {
        return null;
    }
}
