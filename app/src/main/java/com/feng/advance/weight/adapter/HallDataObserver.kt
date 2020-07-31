package com.feng.advance.weight.adapter

interface HallDataObserver {
    fun notifyDataChange()
    fun notifyDataItem(pos: Int)
}