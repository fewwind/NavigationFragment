package com.feng.advance.weight.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.feng.advance.weight.touch.TouchUtil
import kotlin.math.abs

class InnerInterceptView(ctx: Context, att: AttributeSet? = null) : View(ctx, att) {
    var xDown = 0f
    var yDown = 0f
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                xDown = ev.x
                yDown = ev.y
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                var offX = abs(ev.x - xDown)
                var offY = abs(ev.y - yDown)
                parent.requestDisallowInterceptTouchEvent(offX > offY)
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        TouchUtil.touch(this, event)
        return true
//        return super.onTouchEvent(event)
    }
}