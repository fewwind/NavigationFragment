package com.chaozhuo.parentmanager.test.kotlin

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class design {
    var outField = ""
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
            val map = mapOf(1.to("1"), 2.to("1"))
            val listOf = listOf(1, 2)
            var mui = mutableListOf(1, 3)
            val hashMapOf = hashMapOf(1.to(1))

            Proxy.newProxyInstance(javaClass.classLoader, arrayOf(IBuyHouse::class.java), invoke2)
        }
    }

    class Builder(var name: String, var age: Int = 24, var height: Int = 168) {
        fun test() {
            var builder = Builder("", height = 186, age = 36)
        }
    }

    inner class Holder {
        fun getView() {
            println(outField)
            Bean("").copy(name = "name")
            Bean("").name = ""
        }
    }
    data class Bean(var name:String)
}

fun main(args: Array<String>) {
    //嵌套类直接使用外部类，不需要实例化，也不能访问外部类属性
    design.Builder("").test()
    //内部类需要外部实例，可以访问外部类属性
    design().Holder().getView()
}