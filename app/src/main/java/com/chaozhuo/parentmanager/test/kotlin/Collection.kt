package com.chaozhuo.parentmanager.test.kotlin

import com.chaozhuo.parentmanager.bean.AppInfoBean
import java.net.ConnectException
import java.net.SocketException

class Collection {
    fun main() {
        var list = mutableListOf(AppInfoBean(), AppInfoBean())
        var map = mutableMapOf(1 to "1", 2 to "2")//中缀调用
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
    }

    fun varargFun(numA: Int, vararg str: String) {
        // 遍历
        for (s in str) {

        }

// 获取元素
//    str[index]
//    str.component1() ... str.component5()

// 或者其高阶函数用法
//    str.map {  }
//    str.filter {  }
//    str.sortBy {  }

    }
}