package com.chaozhuo.parentmanager.myrxjava

import org.greenrobot.eventbus.Subscribe
import java.lang.reflect.Method
import java.util.concurrent.CopyOnWriteArrayList

object MyBus {
    val subscribeMethods = mutableListOf<SubscribeMethod>()
    var subMethodByEvents = mutableMapOf<Class<*>, CopyOnWriteArrayList<SubscribeMethod>>()
    fun regiest(any: Any) {
        var s = any::class.java
        var a = any.javaClass
        var v = any::javaClass.get()
        var b = Any::class.java
        val methods: Array<Method>
        methods = any::javaClass.get().declaredMethods
        methods.forEach {
            if (it.isAnnotationPresent(Subscribe::class.java)) {
                subscribeMethods.add(SubscribeMethod(any.javaClass, it, it.parameterTypes[0]))
            }
        }
        subscribeMethods.forEach {
            if (subMethodByEvents.containsKey(it.event)) {
                subMethodByEvents.get(it.event)?.add(it)
            } else {
                subMethodByEvents.put(it.event, CopyOnWriteArrayList(arrayListOf(it)))
            }
        }
    }

    fun post(any: Any) {
        subMethodByEvents.get(any.javaClass)?.forEach { it.method.invoke(any, it.event) }
    }
}

class SubscribeMethod(var origin: Class<*>, var method: Method, var event: Class<*>) {
}