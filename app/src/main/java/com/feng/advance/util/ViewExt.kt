package com.feng.advance.util

import android.view.MotionEvent
import android.widget.TextView

fun TextView.setDrawbleClick(onClick: (Int) -> Unit) {
    var left = 0
    var right = 2
    this.setOnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            this.compoundDrawables[left]?.let {
                if (event.getRawX() <= this.left + it.bounds.width()) {
                    onClick(left)
                    return@setOnTouchListener true
                }
            }
            this.compoundDrawables[right]?.let {
                if (event.getRawX() >= this.right - it.bounds.width()) {
                    onClick(right)
                    return@setOnTouchListener true
                }
            }
        }
        return@setOnTouchListener false
    }
}