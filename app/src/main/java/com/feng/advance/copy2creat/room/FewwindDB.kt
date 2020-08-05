package com.feng.advance.copy2creat.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.feng.advance.App
import com.feng.advance.copy2creat.download.DownLoadCenter
import com.feng.advance.copy2creat.download.LoadInfo
import com.feng.advance.util.RxUtil
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Consumer

@Database(entities = [LoadInfo::class], version = 1, exportSchema = false)
abstract class FewwindDB : RoomDatabase() {


    abstract fun getDao(): DateCenterDao

    fun insert(vararg bean: LoadInfo) {
        Single.create<String> { getDao().insertDownInfo(*bean) }.compose(RxUtil.scheduleS()).subscribe()
    }

    fun insertList(data: MutableList<LoadInfo>) {
        Single.create<String> { getDao().insertDownInfos(data) }.compose(RxUtil.scheduleS()).subscribe()
    }

    fun query(url: String, listener: (MutableList<LoadInfo>) -> Unit) {
        Observable.create<MutableList<LoadInfo>> { emitter -> emitter.onNext(getDao().getInfoByUrl(url)) }.compose(RxUtil.schedule()).subscribe(object : Consumer<MutableList<LoadInfo>> {
            override fun accept(t: MutableList<LoadInfo>) {
                listener.invoke(t)
            }
        })
    }


    companion object {
        @JvmStatic
        val daoDB: FewwindDB by lazy {
            Room.databaseBuilder(
                    App.app, FewwindDB::class.java, DownLoadCenter.TABLE
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
}