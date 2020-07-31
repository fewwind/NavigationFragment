package com.feng.advance.copy2creat.picasso

import android.graphics.Bitmap

interface MyCache {
    fun get(key:String):Bitmap
    fun set(key:String,bitmap:Bitmap)
    fun trimSize(max:Int)

}