package com.feng.advance.weight.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.feng.advance.weight.touch.TouchUtil

class InnerInterceptGroup(ctx: Context, att: AttributeSet? = null) : FrameLayout(ctx, att) {
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                return false
            }
            MotionEvent.ACTION_MOVE -> {
                return true
            }
            MotionEvent.ACTION_UP -> {
                return true
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