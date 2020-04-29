package com.chaozhuo.parentmanager.test.kotlin

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.chaozhuo.parentmanager.R
import kotlinx.android.synthetic.main.activity_kotlinm.*

class KotlinmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlinm)
        tv.text = ""
        findViewById<TextView>(R.id.tv).text = ""
        MutableLiveData<String>().observe(this, Observer {
            v->
        })
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
