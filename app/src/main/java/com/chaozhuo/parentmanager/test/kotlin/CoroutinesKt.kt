package com.chaozhuo.parentmanager.test.kotlin

import com.chaozhuo.parentmanager.net.HttpApi
import com.chaozhuo.parentmanager.net.HttpManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CoroutinesKt {
    var httpApi = HttpManager.creatApi(HttpApi::class.java)
    fun main() {
        GlobalScope.launch {
            val token = httpApi.getToken()
            var info2 = async { httpApi.getInfo2(token) }
            var info1 = async { httpApi.getInfo1(token) }
            info1.await() + info2.await()
        }
        GlobalScope.launch(Dispatchers.Main) {
            val token = httpApi.getToken()
            // async 默认是根据上下文，网络请求需要加io
            var info2 = async { httpApi.getInfo2(token) }
            var info1 = async { httpApi.getInfo1(token) }
            info1.await() + info2.await()
        }
    }
}