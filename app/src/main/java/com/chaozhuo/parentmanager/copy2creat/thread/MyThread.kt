package com.chaozhuo.parentmanager.copy2creat.thread

import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

class MyThread {
    var core = 3
    var threadPool = mutableListOf<Thread>()
    var taskList = LinkedBlockingQueue<Worker>()

    fun addTask(task: Runnable) {
        var worker = Worker(task)
        if (threadPool.size < core) {
            threadPool.add(thread {
                while (true) {
                    var real = worker
                    if (real.task == null) real = getTask()
                    real.run()
                }

            })
        } else {
            taskList.put(worker)
        }
    }

    class Worker(var task: Runnable?) : Runnable {
        override fun run() {
            task?.run()
            task = null
        }
    }

    fun getTask(): Worker = taskList.take()

    class LinkBlockQueue {
        var linkList = LinkedList<Runnable>()
        var lock = java.lang.Object()
        fun offer(task: Runnable) {
            synchronized(lock) {
                linkList.add(task)
            }
        }

        fun take(): Runnable {
            synchronized(lock) {
                while (linkList.isEmpty()) lock.wait()
                return linkList.removeFirst()
            }
        }
    }
}