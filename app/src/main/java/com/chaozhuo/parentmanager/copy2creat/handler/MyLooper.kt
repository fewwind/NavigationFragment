package com.chaozhuo.parentmanager.copy2creat.handler

import android.os.Message
import com.orhanobut.logger.Logger
import java.util.*

class MyLooper {
    var qunue: MsgQueue = MsgQueue()

    fun prepare() {
    }

    fun looper() {
        while (true) {
            var msg = qunue.get()
            if (msg != null) {
                Logger.e(msg?.toString())
            }

        }
    }
}

class MsgQueue {
    var list = LinkedList<Message>()
    private var lock: Object = Object()
    fun put(msg: Message) {
        //有锁的地方必须有sync关键字，对锁操作需要在同步代码中
        synchronized(lock) {
            list.push(msg)
            lock.notifyAll()
        }


    }

    fun get(): Message? {
        synchronized(lock) {
            while (list.isEmpty()) {
                lock.wait()
            }
            return list.removeLast()
        }
    }

}