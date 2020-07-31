package com.feng.advance.test.kotlin

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class MyView(ctx: Context, arr: AttributeSet?=null) : TextView(ctx, arr) {
//    constructor(cc: Context)：super(cc)
    companion object{
    //加上注解就可以只用类名调用方法，否则需要.companion
    @JvmStatic
    fun getText(){}
}
}