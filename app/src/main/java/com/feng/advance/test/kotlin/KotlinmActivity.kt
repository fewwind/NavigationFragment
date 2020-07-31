package com.feng.advance.test.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.feng.advance.R
import com.shuo.ruzuo.chat.adapter.HallLayoutAdapter

class KotlinmActivity : AppCompatActivity() {
    lateinit var adapter: HallLayoutAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlinm)
    }

    override fun onResume() {
        super.onResume()
    }

    //属性拓展
    var TextView.isShow: Boolean
        get() {
            return isShown
        }
        set(value) {
            isShow = false
        }

    fun TextView.setExt() = apply { text }
}
