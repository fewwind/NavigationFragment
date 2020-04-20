package com.chaozhuo.parentmanager.test.picasso

import android.graphics.Bitmap
import android.util.LruCache

class FCache : MyCache {
    lateinit var lruCache:LruCache<String, Bitmap>
    init {
        var lruCache = object : LruCache<String, Bitmap>(1024) {
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return value?.byteCount ?: 0
            }
        }
    }

    override fun get(key: String) = lruCache.get(key)

    override fun set(key: String, bitmap: Bitmap) {
        lruCache.put(key,bitmap)
    }

    override fun trimSize(max: Int) {
        lruCache.trimToSize(max)
    }
}