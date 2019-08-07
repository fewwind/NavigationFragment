package com.chaozhuo.parentmanager.test

import com.chaozhuo.parentmanager.bean.AppInfoBean
import com.chaozhuo.parentmanager.test.kotlin.SubClass
import com.orhanobut.logger.Logger
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeoutOrNull

class KotlinTest constructor(value: String) {
    //主构造用constructor修饰，如果没有构造器注解可以省略关键字constructor
    // fun methodName (args : String) :String {} 函数体
    // fun methodName (args : String)  = {} 表达式
    // { args : String -> 函数体，最后一行为返回值} lambda
    val nameFinal = "fa"
    var name = ""
        get() = field
        set(value) {
            if (value.equals("")) {
                field = ""
            } else field = ""
        }
    var intType = 3
        get() = field
        set(value) {
            if (value in 1..6) {
                field = 3
            } else field = 6
        }
    var info = AppInfoBean()
    lateinit var infoNotNull: AppInfoBean //非空属性必须初始化，如果不初始化需要加late关键字
    fun main(args: Array<String>) {
        var listS = listOf<String>("", "")
        listS.filter { it == "" }
        var stringNa: String?
        listS.maxBy { String() }
        for (i in listS.withIndex()) {

        }
        listS.maxBy { s -> s }
        info.order
        info.order = 2
        print(info.activityName?.substring(0))//?空安全，不加的话抛出空指针异常
        intType = Integer.valueOf("daa")
        var condition = if (info.order > 6) 6 else info.order//if的表达式结果赋值给变量
        if (info.order in 1..9) print("在1-9之间")
        when (info.order) {
            1 -> print("order = 1")
            else -> print("not 1")
        }
        val sum = { x: Int, y: Int -> x + y }
        sum(1, 1)
    }

    val lazyValue: String by lazy {
        val temp = SubClass()?.age?.length
        "hello"
    }
    var nullTest: String? = null// ？允许为空
    fun test() = runBlocking {
        //        Logger.e("$lazyValue 非空测试" + nullTest?.substring(0))//！！代表抛出空指针异常
        var items = listOf<String>("a", "b")
        for (item in items) {

        }
        for (index in items.indices) {
//            Logger.w("indices = $index value = ${items[index]}")
        }

        var result = withTimeoutOrNull(1300) {
            repeat(1000) { i ->
                Logger.v("Sleeping $i ..")
                delay(500)
                "Donw"
            }
        }
//        Logger.w(info::class.toString())
//        Logger.w(info::class.java.toString())
        val plus = plus(arg2 = 4, arg1 = 2)
        Logger.e("$plus -> ${sum(1, 1)} Reullt = $result")

    }

    suspend fun plus(arg1: Int, arg2: Int): Int {
        var list = mutableListOf<Int>()
        repeat(1000000, action = { list.add(arg1) })
        repeat(10) {
            list.add(arg1)
        }
        Logger.w("plus")
        return arg1 - arg2
    }

    fun String.isHttpUrl(): Boolean { // 对原有对象的函数拓展,相当于给string对象增加了方法，可以直接调用
        return !this.isEmpty() && this.startsWith("http")
    }

    fun getSName(): String {
        launch {
            plus(1, 1)
            Logger.e("getName")
        }
        return "dong"
    }

    val sum = fun(a: Int, b: Int) = { a + b }
    val sum2 = fun(a: Int, b: Int): Int {
        return a + b
    }
    var tmepBean = object {
        var x: Int = 0
        var y: Int = 1
    }

    fun bidashi(name: String) = {
        print(name)
    }

    companion object {
        fun getName(): String {
            return "静态方法"
        }
    }


}

