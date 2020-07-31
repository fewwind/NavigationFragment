package com.shuo.ruzuo.chat.adapter

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import com.feng.advance.base.ViewHolderRV

abstract class HallLayoutAdapter<T>(
        var ctx: Context?,
        var data: MutableList<T>,
        val layoutId: Int
) : BaseHallAdapter<T, ViewHolderRV>() {
    var time = 0L
    var isDouble = false
    override fun getCount(): Int {
        return data?.size ?: 0
    }

    override fun creatViewHolder(parent: ViewGroup, pos: Int): ViewHolderRV {
        return ViewHolderRV.createViewHolder(ctx, layoutId).apply {
            mIds?.forEach {
                getView<View>(it)?.apply {
                    setOnClickListener {
                        if (SystemClock.elapsedRealtime() - time <= 360) {
                            time = 0L
                            listenerDouble?.invoke(data.get(pos), this, pos)
                            isDouble = true
                        } else {
                            postDelayed({
                                if (!isDouble) listener?.invoke(data.get(pos), this, pos)
                                isDouble = false
                            }, 222)
                        }
                        time = SystemClock.elapsedRealtime()

                    }
                    setOnLongClickListener {
                        listenerLong?.invoke(data.get(pos), this, pos)
                        true
                    }
                }
            }
        }
    }

    override fun bindViewholder(holder: ViewHolderRV, pos: Int) {
        converHolder(holder, data.get(pos))
    }

    abstract fun converHolder(holder: ViewHolderRV, t: T)
}