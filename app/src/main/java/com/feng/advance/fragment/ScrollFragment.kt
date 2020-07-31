package com.feng.advance.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.feng.advance.R
import com.feng.advance.base.BaseFragment
import com.feng.advance.base.CommonAdapterRV
import com.feng.advance.base.ViewHolderRV
import com.feng.advance.util.DataUtil

class ScrollFragment : BaseFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }


    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_scroll
    }


    override fun initViewsAndEvents(view: View) {

    }

    /*    private boolean dispatchTouchEvent() {
        boolean isHand = false;
        if (onInterceptTouchEvent()) {
            isHand = super.dispatchTouchEvent();// super就是View源码，默认调用onTouchEvent();
        } else {
            isHand = child.dispatchTouchEvent();
        }
        return isHand;
    }*/


    private fun setAdapter(v: View) {
        val rv = v.findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv.adapter = object : CommonAdapterRV<String>(activity, DataUtil.creatSimlpe(20),
                R.layout.item_viewpager) {
            override fun convert(holder: ViewHolderRV, s: String) {

            }
        }
    }

    companion object {
        fun newInstance(): Fragment {
            val fragment = ScrollFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
