package com.chaozhuo.parentmanager.test.kotlin

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.chaozhuo.parentmanager.R
import kotlinx.android.synthetic.main.activity_kotlinm.*

class KotlinmActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlinm)
        tv.text = ""
        findViewById<TextView>(R.id.tv).text = ""
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
