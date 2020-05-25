package com.chaozhuo.parentmanager.weight.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.chaozhuo.parentmanager.base.ViewHolderRV

abstract class HallLayoutAdapter<T>(
        var ctx: Context?,
        var data: MutableList<T>?,
        val layoutId: Int) : BaseHallAdapter<T, ViewHolderRV>() {

    override fun getCount(): Int {
        return data?.size ?: 0
    }

    override fun creatViewHolder(parent: ViewGroup, pos: Int): RecyclerView.ViewHolder {
        return ViewHolderRV.createViewHolder(ctx, layoutId).apply {
            mIds?.forEach {
                getView<View>(it)?.apply {
                    setOnClickListener { listener?.invoke(data?.get(pos), this) }
                }
            }
        }
    }

    override fun bindViewholder(holder: ViewHolderRV, pos: Int) {
        converHolder(holder, data?.get(pos))
    }

    abstract fun converHolder(holder: ViewHolderRV, t: T?)
}