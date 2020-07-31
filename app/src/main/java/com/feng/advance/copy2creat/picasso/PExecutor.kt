package com.feng.advance.copy2creat.picasso

import java.util.concurrent.Future
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class PExecutor : ThreadPoolExecutor {
    constructor() : super(3, 3, 0, TimeUnit.SECONDS, PriorityBlockingQueue<Runnable>())

    override fun <T : Any?> submit(task: Runnable?, result: T): Future<T> {
        return super.submit(task, result)
    }
}