package com.chaozhuo.parentmanager.test.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.chaozhuo.parentmanager.App
import com.chaozhuo.parentmanager.R
import com.chaozhuo.parentmanager.activity.SplashActivity


class ResourceID : View.OnClickListener {
    override fun onClick(view: View) {
        when (view.id) {
            R.id.appbar -> {
            }
        }
    }

    val image: String = "1"
    val text: String = "2"
    // 语句和表达式的区别就是表达式有值，语句没有，如果函数体由单个表达式构成，可以省去花括号和return，这种叫语法表达式函数体
    fun add(a: Int, b: Int) = a + b

    val sum = fun(a: Int, b: Int) = a + b

    fun test() {
        val base = BaseImp(2)
        Derived(base).print()
        val tv = TextView(null)
        tv.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            }
        })
        //lambda 正常表示 小括号包含 表达式
        tv.setOnClickListener({ v -> v.visibility = View.VISIBLE })
        //lambda 是函数最后一个参数时，可以放到括号外边
        tv.setOnClickListener() { v -> v.visibility = View.VISIBLE }
        //当函数只有一个lambda 参数时，可以省略括号
        tv.setOnClickListener { v -> v.visibility = View.VISIBLE }
        //lambda 只有一个参数时，可以省去参数列表，在表达式部分用it引用参数
        tv.setOnClickListener { it.visibility = View.VISIBLE }

        App.app.startActivity(Intent(App.app, SplashActivity::class.java).apply {
            this.action = ""
            this.putExtras(Bundle().apply {
                putString("info", "ss")
            })
        })
        val listener = View.OnClickListener { v -> }
        val listener2 = { v: View -> }
        val lambdaTest: (c: Int, d: Int) -> Int = { a, b -> a + b }
        val lambdaTest2 = { a: Int, b: Int -> a + b }
        val lambdaTest3 = { print("") }
    }

}

fun repeat2(data: Int, action: (Int) -> Unit) {
    for (i in 0..data) {
        action(i)
    }
}

interface Function1<in T, out Boolean> {
    operator fun invoke(p1: T): kotlin.Boolean
}

interface Base {
    fun print()
}

class BaseImp(val i: Int) : Base {
    override fun print() {
    }

}

class Derived(base: Base) : Base by base // Dervied 的所有实现委托给base

data class 猪(val 编号: String, val weight: Int)

val pigs = listOf(猪("9527", 50),
        猪("007", 48),
        猪("009", 45))

fun main(args: Array<String>) {

    var pig: 猪? = null
    var maxWeight = 0;
    pigs.forEach {
        if (it.weight > maxWeight) {
            pig = it;
            maxWeight = it.weight
        }
    }

    println(pig)
    pigs.maxBy { p: 猪 -> p.weight }
    pigs.maxBy { p -> p.weight }//自动推导
}