package com.chaozhuo.parentmanager.weight.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.chaozhuo.parentmanager.base.ViewHolderRV

abstract class HallLayoutAdapter<T>(var ctx: Context, var data: MutableList<T>?, val layoutId: Int) : BaseHallAdapter<ViewHolderRV>() {

    override fun getCount(): Int {
        return data?.size ?: 0
    }

    override fun creatViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolderRV.createViewHolder(ctx, layoutId)
    }

    override fun bindViewholder(holder: ViewHolderRV, pos: Int) {
        converHolder(holder, data?.get(pos))
    }

    abstract fun converHolder(holder: ViewHolderRV, t: T?)
}