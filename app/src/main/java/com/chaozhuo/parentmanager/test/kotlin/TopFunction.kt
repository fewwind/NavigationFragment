package com.chaozhuo.parentmanager.test.kotlin

import android.text.format.DateUtils
import com.chaozhuo.parentmanager.App

fun formartTime(time: Long): String {
    return DateUtils.formatDateTime(App.app, time, 1)
}

//同类型多个参数，直接指定变量名，避免参数错位
fun multArg(one: String, two: String, three: String) {

}

fun strEques(a: String, b: String): Boolean {
    var list = listOf("1", "2")
    multArg(one = "1", three = "3", two = "2")
    return false
}