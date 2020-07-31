package com.feng.advance.weight.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.feng.advance.weight.touch.TouchUtil

class OutInterceptView(ctx: Context, att: AttributeSet? = null) : View(ctx, att){
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        TouchUtil.touch(this,event)
        return true
//        return super.onTouchEvent(event)
    }
}