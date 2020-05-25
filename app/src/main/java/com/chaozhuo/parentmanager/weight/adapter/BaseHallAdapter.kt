package com.chaozhuo.parentmanager.weight.adapter

import android.database.Observable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class BaseHallAdapter<T, VH : RecyclerView.ViewHolder> : HallDataObserver {
    var mIds: IntArray? = null
    var listener: ((t: T?, view: View) -> Unit)? = null
    var observable = HallAdapterDataObservable()
    abstract fun getCount(): Int
    abstract fun creatViewHolder(parent: ViewGroup, pos: Int): RecyclerView.ViewHolder
    abstract fun bindViewholder(holder: VH, pos: Int)
    fun registerAdapterObserver(observer: HallDataObserver) {
        observable.registerObserver(observer)
    }

    fun unRegisterAdapterObserver(observer: HallDataObserver) {
        observable.unregisterObserver(observer)
    }

    override fun notifyDataChange() {
        observable.notifyDataChange()
    }

    override fun notifyDataItem(pos: Int) {
        observable.notifyDataItem(pos)
    }

    fun addClickId(vararg ids: Int) {
        mIds = ids
    }

    fun onItemClick(listener: (t: T?, v: View) -> Unit) {
        this.listener = listener
    }
}

interface HallDataObserver {
    fun notifyDataChange()
    fun notifyDataItem(pos: Int)
}

class HallAdapterDataObservable : Observable<HallDataObserver>() {
    fun notifyDataChange() {
        mObservers?.forEach {
            it.notifyDataChange()
        }
    }

    fun notifyDataItem(pos: Int) {
        mObservers?.forEach {
            it.notifyDataItem(pos)
        }
    }
}


