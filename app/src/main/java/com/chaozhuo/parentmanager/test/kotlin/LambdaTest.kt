package com.chaozhuo.parentmanager.test.kotlin

import android.view.View
import android.widget.TextView
import com.chaozhuo.parentmanager.base.ViewHolder

class LambdaTest {
    //https://blog.csdn.net/u013064109/article/details/78786646
    //https://www.cnblogs.com/Jetictors/p/8647888.html
    var tv: TextView = TextView(null)
    var empty = tv ?: ""
    var 变量名 = {
        //操作的代码
        LambdaTest().test(1, { })
        LambdaTest().test(2) { }
        LambdaTest().test2(2) { }
    }
    var 变量参数: (Int) -> Int = {
        //操作的代码
        1
    }
    //等价于
    var 变量参数2 = { arg: Int ->
        //操作的代码
        1
    }

    fun setOnclick() {
        tv.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            }
        })
        var clickFunction = {}//表达式只是个fun，虽然能作为listener参数传递给onclickListener，但它并不是Listener对象，编译后加了一层包装而已
        var clickListener = View.OnClickListener {}
        tv.setOnClickListener({ v: View -> })
        tv.setOnClickListener() { v: View -> }//如果最后一个参数是lambda，可以放在后边
        tv.setOnClickListener { v: View -> }//如果方法只有一个参数，小括号省略
        tv.setOnClickListener { }//如果表达式只有一个参数，参数省略，类型也可以省略.用it
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
        tv?.apply {
            visibility = View.GONE
            text = ""
        }
    }

    //表达式作为参数，参数名 ，参数类型，返回值
    fun test(a: Int, argName: (arg: Int) -> Unit) {
        argName(1)

    }

    fun test2(a: Int, argName: () -> Unit) {
        val runnable = Runnable { argName }
        argName()
    }

    var listener: ((Int, String) -> Unit)? = null
    //kotlin直接调用java的匿名内部类直接使用lambda，但是如果接口是kotlin写的不行
    fun setOnListener(listener: (pos: Int, item: String) -> Unit) {
        this.listener = listener
        //-----use
        LambdaTest().setOnListener { pos, item -> }
    }

    fun bindViewHolder(holder: ViewHolder, pos: Int, item: String) {
        tv.setOnClickListener { listener?.invoke(pos, item) }
    }
    //每个lambda都会被编译成对象，内联函数不会（直接调用函数体），可以优化代码
    inline fun <T, R> T.myLet(block: (T) -> R): R {
        return block(this)
    }
}