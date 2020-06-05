package com.chaozhuo.parentmanager.copy2creat.picasso

import android.content.Context
import com.squareup.picasso.Cache
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PPicasso {
    companion object

   inner class Builder(context: Context) {
        val ctx: Context by lazy {
            context.applicationContext
        }
        lateinit var executor: ExecutorService
        lateinit var cache: Cache
        init {
            executor = Executors.newFixedThreadPool(3)
        }
    }
}