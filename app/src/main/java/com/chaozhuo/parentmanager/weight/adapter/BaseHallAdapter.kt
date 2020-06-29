package com.shuo.ruzuo.chat.adapter

import android.database.Observable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.chaozhuo.parentmanager.base.ViewHolderRV
import com.chaozhuo.parentmanager.weight.adapter.HallDataObserver

abstract class BaseHallAdapter<T, VH : RecyclerView.ViewHolder> : HallDataObserver {
    var mIds: IntArray? = null
    var listener: ((t: T?, view: View, pos: Int) -> Unit)? = null
    var listenerLong: ((t: T?, view: View, pos: Int) -> Unit)? = null
    var listenerDouble: ((t: T?, view: View, pos: Int) -> Unit)? = null
    var observable = HallAdapterDataObservable()
    abstract fun getCount(): Int
    abstract fun creatViewHolder(parent: ViewGroup, pos: Int): ViewHolderRV
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

    fun addClickId(vararg ids: Int) {
        mIds = ids
    }

    override fun notifyDataItem(pos: Int) {
        observable.notifyDataItem(pos)
    }

    fun onItemClick(listener: (t: T?, v: View, pos: Int) -> Unit) {
        this.listener = listener
    }
    fun onDoubleClick(listener: (t: T?, v: View, pos: Int) -> Unit) {
        this.listenerDouble = listener
    }
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


