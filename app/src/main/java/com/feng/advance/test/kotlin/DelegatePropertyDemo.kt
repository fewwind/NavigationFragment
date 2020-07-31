package com.feng.advance.test.kotlin

import android.content.Intent
import kotlin.reflect.KProperty

class DelegatePropertyDemo {
    var content: String by Content()

}

class User(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int by map
    fun main() {
        val user = User(mutableMapOf("name" to "tony", "age" to 22))
        Intent().apply {  }
    }
}

class Content {
    operator fun getValue(delegatePropertyDemo: DelegatePropertyDemo, property: KProperty<*>): String {
        return "${delegatePropertyDemo} property ${property.name} = orgin"
    }

    operator fun setValue(delegatePropertyDemo: DelegatePropertyDemo, property: KProperty<*>, s: String) {
        print("${delegatePropertyDemo} property ${property.name} is set value $s ")
    }

}