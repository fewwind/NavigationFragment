package com.chaozhuo.parentmanager.test.kotlin

import android.animation.AnimatorSet
import com.chaozhuo.parentmanager.bean.AppInfoBean
import com.chaozhuo.parentmanager.bean.AppUsageBean
import java.net.ConnectException
import java.net.SocketException

class MyCollection {
    var list = mutableListOf(AppInfoBean(), AppInfoBean())
    fun main() {

        var map = mutableMapOf(1 to "1", 2 to "2")//中缀调用,需要关键字infox
        var map2 = mutableMapOf(1.to("1"), 2 to "2")
        for (i in 1..9) {
        }
        for (index in 1 until 9) {
        }//9闭区间
        for (index in 9 downTo 1) {
        }
        for (index in 1..9 step 2) {
        }
        var newList = list.map { it.activityName }
        list.forEach { it }
//        map.forEach { key, value -> }
        for (index in list.indices) {
            print("$index")
        }
        for ((index, element) in list.withIndex()) {
            print("$index--$element")
        }
        for (element in list) {
            print("$element")
        }
        for ((key, value) in map) {
        }
        map.mapValues { (key, value) -> }
        list.map { AppUsageBean() }
        list.filter { it.order > 0 }
    }

    fun whenTest() {
        var a = 2
        when (a) {
            1 -> 1
        }
        var e: Exception? = null
        when (e) {
            is SocketException -> {
            }
            is ConnectException -> {
            }
        }
        LambdaTest().also { tt -> tt.setOnclick() }
    }

    fun varargFun(numA: Int, vararg str: String) {
        // 遍历
        for (s in str) {
            setMap2 { info -> info.activityName }
            setMap2 { it.activityName }
            var res = AppInfoBean().activityName
            list.print { "${it.activityName}" }
        }

// 获取元素
//    str[index]
//    str.component1() ... str.component5()

// 或者其高阶函数用法
//    str.map {  }
//    str.filter {  }
//    str.sortBy {  }

    }

    fun setMap2(mapp: (info: AppInfoBean) -> String) {
    }

    var map: ((AppInfoBean) -> String)? = null

    fun <T> Collection<T>.print(map: (T) -> String) = StringBuilder("\n[ ").also { sb ->
        this.forEach { e ->
            sb.append("${map(e)}")
            sb.append(" ]")
            AnimatorSet().addListener { activityName }
        }
    }.toString()

    var action: (AppInfoBean.() -> Unit)? = null
    fun AnimatorSet.addListener(action: AppInfoBean.() -> Unit) {
    }

    var ll = fun(x: Int, y: Int): Int = x + y
}