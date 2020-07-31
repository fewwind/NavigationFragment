package com.feng.advance.test.kotlin

import android.content.Context
import android.content.SharedPreferences
import com.feng.advance.App
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(private val key: String, private val default: T) : ReadWriteProperty<Any?, T> {
    companion object {
        val preference: SharedPreferences by lazy { App.app.getSharedPreferences("", Context.MODE_PRIVATE) }
        fun clear() {
            preference.edit().clear().apply()
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharePreference(key, default)
    }

    private fun <T> getSharePreference(key: String, default: T): T = preference?.run {
        val value: Any = when (default) {
            is Long -> getLong(key, default)
            else -> throw IllegalArgumentException(" type is illega")
        }
        value as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    }

    fun main(arg: Array<String>) {
        var isLogin: Boolean by Preference("islogin", false)
    }
}