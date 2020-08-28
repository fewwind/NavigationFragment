package com.feng.advance.weight.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ListView
import android.widget.ScrollView
import com.feng.advance.R
import com.feng.advance.util.DensityUtil
import com.feng.advance.weight.touch.TouchUtil
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_scroll_conflict.view.*
import java.util.jar.Attributes

class OutScrollView(ctx: Context, att: AttributeSet) : ScrollView(ctx, att) {
    lateinit var mLv: ListView
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Logger.v("${ev.y < DensityUtil.dip2px(150f)}")
        // 理论上y坐标小于150，不拦截事件，交给子view处理，但是任然能够滑动，后来发现是子Text没有click，所以又返回给父的ontouchevent
//        if (ev.y < DensityUtil.dip2px(150f)) {
//            return false
//        }
        // 判断触摸点在listview范围就不拦截交给子view处理
        if (isContain(ev.y.toInt())) {
            return false
        }
        return super.onInterceptTouchEvent(ev)
    }

    fun isContain(y: Int): Boolean {
        return y > mLv.top && y < mLv.bottom
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mLv = findViewById(R.id.lv)
    }
}