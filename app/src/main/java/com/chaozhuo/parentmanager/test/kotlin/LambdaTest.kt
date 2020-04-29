package com.chaozhuo.parentmanager.test.kotlin

import android.os.Handler
import android.view.View
import android.widget.TextView
import com.chaozhuo.parentmanager.base.ViewHolder
import com.chaozhuo.parentmanager.bean.TestBean

class LambdaTest {
    //https://blog.csdn.net/u013064109/article/details/78786646
    //lambda表达式详解，系列黄色背景-> https://www.cnblogs.com/Jetictors/p/8647888.html
    var tv: TextView = TextView(null)
    var empty = tv ?: ""
    var 变量名 = {
        //操作的代码
        LambdaTest().test(1, { })
        LambdaTest().test(2) { v -> v * 10 }
        LambdaTest().test2(2) { }
    }
    var 变量参数: (Int) -> Int = { arg ->
        //操作的代码
        arg + 1
    }
    var 变量参数1: (Int) -> Int = {
        //操作的代码
        it + 1
    }
    //等价于
    var 变量参数2 = { arg: Int ->
        arg + 1
    }
    var noArg = { }//无参
    var onClick = { v: View -> Boolean }
    /**
     * 总结lambda的几种写法
     * 1，var name = {i:Int -> i-1}
     * 2,var name :(Int) -> Int = {arg -> arg+1}
     * 3,(2的简写)var name :(Int) -> Int = {it+1}
     * ------
     * 只定义参数和返回值，不定义具体函数体,注意冒号和等号，使用的时候如：44
     * 4,var name4 : ((View)-> Unit)? = null
     * 44，name4 = { v-> } = {},两种都可以
     */
    // lambda 作为参数
    fun testLambda(i: Int, arglambda: (num1: Int, num2: Int) -> Int): Int {
        arglambda(3, 5)
        //给lambda传递参数，具体规则取决于调用者
        return i + arglambda.invoke(3, 5)
    }


    fun setOnclick() {
        testLambda(1, { a: Int, b: Int -> a * b })
        testLambda(1) { a, b -> a + b }
        //---
        tv.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            }
        })
        var clickFunction = {}//表达式只是个fun，虽然能作为listener参数传递给onclickListener，但它并不是Listener对象，编译后加了一层包装而已
        var clickListener = View.OnClickListener {}
        tv.setOnClickListener({ v: View -> v.alpha = 0f })
        tv.setOnClickListener() { v: View -> v.alpha = 0f }//如果最后一个参数是lambda，可以放在后边
        tv.setOnClickListener { v: View -> v.alpha = 0f }//如果方法只有一个参数，小括号省略
        tv.setOnClickListener { it.visibility = View.VISIBLE }//如果表达式只有一个参数，参数省略，类型也可以省略.用it
        tv.setOnClickListener(View.OnClickListener { })//如果表达式只有一个参数，参数省略，类型也可以省略
        //---
        var letReturn = tv?.let {
            it.visibility = View.VISIBLE
            it.text = ""
            100
        }
        //let返回闭包最后一行，also是返回对象
        var tvObject = tv?.also {
            it.visibility = View.VISIBLE
            it.text = ""
            100
        }
        //参数不可为null.表达式内部直接使用对象的属性
        var withReturn = with(tv) {
            this.text = "with,this is tv"
            text = "with,this is tv"
        }
        //适用于let,with函数任何场景。因为run函数是let,with两个函数结合体，准确来说它弥补了let函数在函数体内必须使用it参数替代对象，
        // 在run函数中可以像with函数一样可以省略，直接访问实例的公有属性和方法，另一方面它弥补了with函数传入对象判空问题，在run函数中可以像let函数一样做判空处理
        //原文链接：https://blog.csdn.net/u013064109/article/details/78786646
        tv?.run {
            visibility = View.GONE
            text = ""
        }
        // apply和run很像，区别是返回值是当前对象，run返回最后一行
        //方法解构：相当于接受一个无参的lambda表达式
        tv?.apply {
            visibility = View.GONE
            text = ""
            isShown
        }
    }

    //表达式作为参数，参数名 ，参数类型，返回值
    fun test(a: Int, argName: (arg: Int) -> Unit) {
        argName(1)

    }

    fun test2(a: Int, argName: () -> Unit) {
        val runnable = Runnable { argName }
        Handler().post(runnable)
    }

    var myListener: ((a: Int, b: String) -> Unit)? = null//参数写法
    var myListener2 = { i: Int, s: String -> Unit }//表达式写法
    //kotlin直接调用java的匿名内部类直接使用lambda，但是如果接口是kotlin写的不行
    fun setOnListener(listener: (a: Int, s: String) -> Unit) {
        this.myListener = listener
        myListener2 = listener
        myListener2(1, "")
        //-----use
        LambdaTest().setOnListener { pos, item -> }
    }

    fun bindViewHolder(holder: ViewHolder, pos: Int, item: String) {
        tv.setOnClickListener { myListener?.invoke(pos, item) }
    }

    //每个lambda都会被编译成对象，内联函数不会（直接调用函数体），可以优化代码
    inline fun <T, R> T.myLet(block: (T) -> R): R {
        return block(this)
    }

    fun <T> T.myApply(block: T.() -> Unit): T {
        block()
        return this
    }

    fun lambdaArg() {
        var s = "abc"
        // sumBy函数接受一个表达式，参数是char，返回值是int，，实际的参数来源是方法内部，根据参数转为int，这个方法是自己定义的，
        // 比如直接写2，就是3个2相加，传一个char。根据char自定义生成int，这就是一个规则。真正调用是方法内部
        var sumBy = s.sumBy { c: Char -> c.toInt() }
        sumBy = s.sumBy { it.toInt() }
        sumBy = s.sumBy { 2 }
    }

    //SAM格式
    fun testSam() {
//        SamTest().setSamType { type -> } 编译器不知道改调用哪个了
        SamTest().setSamType(SamTest.ISAM1 { type -> })
        SamTest().setSamType({ } as SamTest.ISAM2)
        SamTest().setSamType(object : SamTest.ISAM1 {
            override fun getType(type: Int) {
            }
        })

    }

}

fun main(args: Array<String>) {
    var test = HTML()
    test.setClickL { println(it.id) }

    html {
        title = "new"
        body()
    }
    test.onClick.invoke(TestBean(1, 0))
}

//带接收者的函数字面值以及lambda的各种写法
class HTML {
    var title = "old"
    var onClick = { t: TestBean -> Unit }
    var onClick2: ((t: TestBean) -> Unit)? = null
    var onClick3: ((TestBean) -> Unit)? = null
    fun body() {
        println("${onClick(TestBean())}<>$title<>}")
    }

    fun setClickL(click: (TestBean) -> Unit) {
        this.onClick = click
        this.onClick2 = click
        this.onClick3 = click
    }

}

fun html(init: HTML.() -> Unit): HTML {
//        return HTML().apply(init) 简写
    val html = HTML()
    html.init()
    return html
}

fun methodQuote(args: Array<String>) {
    fun String.isEques(s: String) = this.equals(s)
    //注意是小括号，普通lambda是大括号
    args.forEach(::println)
    //此方法接受参数为string，返回值为boolean的lambda，而方法引用是直接调用比如下边的isNotEmpty，其实没有参数，但是引用String后，String就是方法参数
    args.filter(String::isNotEmpty)
    //增加一个参数为string，返回值为bool的方法，就会报错，因为直接用类相当于类是第一个参数，需要用对象调用
//    args.filter(String::isEques)
    var instancs = String()
    args.filter(instancs::isEques)
}