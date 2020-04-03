package com.chaozhuo.parentmanager.test.kotlin

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class design {

    class HouseOwer() : IBuyHouse {
        override fun askPrice() {
        }

        override fun payMoney() {
        }

        override fun visitHouse() {
        }


    }

    class HouseProxy(val buyHouse: IBuyHouse) : IBuyHouse by buyHouse {
        var invoke = object : InvocationHandler {
            override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
                return method?.invoke(args) as Any
            }
        }
        var invoke2 = InvocationHandler { proxy: Any?, method: Method?, args: Array<out Any>? ->
            method?.invoke(args)
        }
        var invoke3 = InvocationHandler { proxy, method, args ->
            method?.invoke(args)
        }

        override fun askPrice() {
            val map = mapOf(1.to("1"),2.to("1"))
            val listOf = listOf(1, 2)
            var mui = mutableListOf(1,3)
            val hashMapOf = hashMapOf(1.to(1))

            Proxy.newProxyInstance(javaClass.classLoader, arrayOf(IBuyHouse::class.java), invoke2)
        }
    }
}