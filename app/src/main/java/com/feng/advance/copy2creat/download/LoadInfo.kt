package com.feng.advance.copy2creat.download

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class LoadInfo(@PrimaryKey(autoGenerate = true)
                    var id: Int, var start: Long, val end: Long, val url: String) {

    companion object {
        const val URL = "url"
    }

    override fun toString(): String {
        return "LoadInfo(start=$start, end=$end, url='$url', id=$id)"
    }

}