package com.feng.advance.copy2creat.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.feng.advance.copy2creat.download.DownLoadCenter
import com.feng.advance.copy2creat.download.LoadInfo

@Dao
interface DateCenterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDownInfo(vararg infos: LoadInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDownInfos(infos: MutableList<LoadInfo>)

    @Query("SELECT * FROM " + DownLoadCenter.TABLE + " where " + LoadInfo.URL + " = :url")
    fun getInfoByUrl(url: String): MutableList<LoadInfo>
}