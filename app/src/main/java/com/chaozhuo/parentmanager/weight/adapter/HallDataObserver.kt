package com.chaozhuo.parentmanager.weight.adapter

interface HallDataObserver {
    fun notifyDataChange()
    fun notifyDataItem(pos: Int)
}