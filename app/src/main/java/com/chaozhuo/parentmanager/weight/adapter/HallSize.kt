package com.chaozhuo.parentmanager.weight.adapter

import com.chaozhuo.parentmanager.util.DensityUtil
import com.orhanobut.logger.Logger

data class HallSize(var itemW: Int, var bgRadius: Int) {
    var avatarRadius: Int
    var width: Int

    init {
        itemW = DensityUtil.dip2px(itemW.toFloat())
        bgRadius = DensityUtil.dip2px(bgRadius.toFloat())
        avatarRadius = (itemW * 0.7f / 2).toInt()
        width = bgRadius * 4
        Logger.w("item = $itemW radius = $bgRadius <> $avatarRadius <> $width")
    }

    companion object {
        fun creat() = HallSize(68, 60)
    }
}