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

class InnerListlView(ctx: Context, att: AttributeSet) : ListView(ctx, att) {

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}