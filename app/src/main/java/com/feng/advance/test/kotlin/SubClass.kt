package com.feng.advance.test.kotlin

import com.orhanobut.logger.Logger

fun main(args: Array<String>) {

}

class SubClass {
    var age: String? = "23"
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum1(a: Int, b: Int) = a + b //表达式作为返回值，自动推断类型
    public fun sum2(a: Int, b: Int): Int = a + b//public 必须返回值
    public fun sum3(a: Int, b: Int) {
        //如果无返回值，public 也可以不写返回值，没返回值是特殊的返回值Unit
        Logger.e("$age,zifuchuan ${sum(1,1)}")
    }

    fun vars(vararg v: Int) {//vararg 可变参数
        for (data in v) {
            print(data)
        }
        for (data in v) print(data)

    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String)
            return obj.length

        return null
    }

    fun forTest() {
        for (i in 1..9) {

        }
        for (i in 1.."name".length) {

        }
        for (i in 1 until 9) {
            //不包含9
        }
    }

}