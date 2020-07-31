package com.feng.advance.weight.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.feng.advance.weight.touch.TouchUtil
import kotlin.math.abs

class OutInterceptGroup(ctx: Context, att: AttributeSet? = null) : FrameLayout(ctx, att) {
    var xDown = 0f
    var yDown = 0f
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                xDown = ev.x
                yDown = ev.y
                return false
            }
            MotionEvent.ACTION_MOVE -> {
                var offX = abs(ev.x - xDown)
                var offY = abs(ev.y - yDown)
                return offX > offY
            }
            MotionEvent.ACTION_UP -> {
                return false
            }
            else -> return false
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        TouchUtil.touch(this, event)
        return true
//        return super.onTouchEvent(event)
    }
}