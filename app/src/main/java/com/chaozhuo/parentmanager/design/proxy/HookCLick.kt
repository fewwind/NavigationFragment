package com.chaozhuo.parentmanager.design.proxy

import android.view.View
import com.orhanobut.logger.Logger
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class HookCLick {
    fun proxyClick(view: View) {
        var getInfoM = View::class.java.getDeclaredMethod("getListenerInfo")
        getInfoM.isAccessible = true
        var listenerInfo = getInfoM.invoke(view)
        var infoClass = Class.forName("android.view.View\$ListenerInfo")
        var field = infoClass.getDeclaredField("mOnClickListener")
        field.isAccessible = true
        var clickListener = field.get(listenerInfo)
        var proxyClick = Proxy.newProxyInstance(
                View::class.java.classLoader,
                arrayOf(View.OnClickListener::class.java),
                object : InvocationHandler {
                    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
                        Logger.e(" -> ${method?.name}")
                        // 注意参数传的view
                        return method?.invoke(clickListener, view)
                    }
                })
        field.set(listenerInfo, proxyClick)
    }

}