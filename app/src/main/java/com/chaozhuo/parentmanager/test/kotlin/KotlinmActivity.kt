package com.chaozhuo.parentmanager.test.kotlin

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.chaozhuo.parentmanager.R
import com.chaozhuo.parentmanager.base.ViewHolderRV
import com.chaozhuo.parentmanager.weight.ChatHallInfoView
import com.chaozhuo.parentmanager.weight.adapter.HallLayoutAdapter
import com.chaozhuo.parentmanager.weight.adapter.HallSize
import com.orhanobut.logger.Logger

class KotlinmActivity : AppCompatActivity() {
    lateinit var adapter: HallLayoutAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlinm)
        MutableLiveData<String>().observe(this, Observer { v ->
        })
        var data = MutableList(6) { it.toString() }
        var chatView = findViewById<ChatHallInfoView>(R.id.hall)
        adapter = object : HallLayoutAdapter<String>(this, data, R.layout.view_broadcast_info) {
            override fun converHolder(holder: ViewHolderRV, t: String?) {
                Logger.w("bind = $t")
                holder.getView<TextView>(R.id.name).text = t
            }
        }
        chatView.setHallBg(HallSize.creat(), R.drawable.hall_oval_black)
        chatView.setAdapter(adapter)
        findViewById<View>(R.id.view).setOnClickListener { adapter.notifyDataChange() }
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
