package com.feng.advance.copy2creat.download

import com.feng.advance.App
import com.feng.advance.copy2creat.room.FewwindDB
import com.orhanobut.logger.Logger
import okhttp3.*
import java.io.BufferedInputStream
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

object DownLoadCenter {
    const val TABLE = "LoadInfo"
    var path: String = App.app.externalCacheDir.absolutePath + File.separator + "shuo.apk"
    const val address = "http://files.360shuo.cn/app/ruzuo_private_beta_v0.9.6_07301340.apk"
    const val wzry = "https://dlied4.myapp.com/myapp/1104466820/cos.release-40109/10040714_com.tencent.tmgp.sgame_a952605_1.61.1.6_uifan3.apk"
    var mTasks = mutableListOf<DownLoadTask>()
    val client: OkHttpClient by lazy {
        OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES).connectTimeout(3, TimeUnit.MINUTES).build()
    }
    val pool: ExecutorService by lazy {
        Executors.newFixedThreadPool(4)
    }

    fun startLoad(url: String) {
        FewwindDB.daoDB.query(url) {
            var file = File(path)
            if (!file.exists()) file.createNewFile()
            else {
                file.delete()
                file.createNewFile()
            }
            if (it.isEmpty()) {
                client.newCall(Request.Builder().url(url).build()).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                    }

                    override fun onResponse(call: Call, response: Response) {
                        var length = response.body()?.contentLength() ?: 0
                        var segment = (length / 3)
                        //分段范围包头也包尾
                        var list = arrayOf(LoadInfo(1, 0, segment - 1, url), LoadInfo(2, segment, segment * 2 - 1, url), LoadInfo(3, segment * 2, length, url))
                        list.forEach {
                            Logger.w("$length $it")
                            var task = DownLoadTask(false, it)
                            mTasks.add(task)
                            pool.submit(task)
                        }
                    }

                })

            } else {
                Logger.v("   <> ${it.size}")
                it.forEach { info ->
                    info.start = info.start + 1
                    var task = DownLoadTask(false, info)
                    mTasks.add(task)
                    pool.submit(task)
                }
            }
        }
    }

    fun stop(url: String) {
        mTasks.forEach {
            if (it.info.url == url) {
                it.stop = true
            }
        }
        Logger.e("${mTasks.size}")
    }


    fun downFile() {
        pool.submit(task)
    }
    fun stopFile() {
        (pool as ThreadPoolExecutor).remove(task)
    }

    var task = object : Runnable {
        override fun run() {
            var call = client.newCall(Request.Builder().url(wzry).build())
            var response = call.execute()
            Logger.w(" ${response}")
            response.body().toString()
            var isByte = response.body()?.byteStream()
            var bis = BufferedInputStream(isByte)
            var file = File(path)
            var randomAccess = RandomAccessFile(file, "rwd")
            var buffer = ByteArray(2048)
            var len = 0
            try {
                while (len != -1 && !Thread.currentThread().isInterrupted) {
                    len = bis.read(buffer)
                    randomAccess.write(buffer, 0, len)
                    Logger.v(" ${len}  <> ${randomAccess.length()}")
                }
            } catch (e: Exception) {
                Logger.e("e = $e")
            } finally {
                isByte?.close()
                randomAccess.close()
                bis.close()
            }
        }
    }
}

class DownLoadTask(var stop: Boolean = false, var info: LoadInfo) : Runnable {

    override fun run() {//44257221

        // 如果传了range字段，服务端返回的length是range的长度，比如（1-100），返回的length就是100
        var call = DownLoadCenter.client.newCall(Request.Builder().header("RANGE", "bytes=" + info.start + "-" + info.end).url(info.url).build())
        var response = call.execute()
        var isByte = response.body()?.byteStream()
        var bis = BufferedInputStream(isByte)
        var file = File(DownLoadCenter.path)
        var randomAccess = RandomAccessFile(file, "rwd")
        var buffer = ByteArray(2048)
        randomAccess.seek(info.start)
        var len = 0
        var total = 0
        try {
            while (len != -1) {
                if (stop) {
                    DownLoadCenter.mTasks.remove(this)
                    call.cancel()
                    break
                }
                randomAccess.write(buffer, 0, len)

                info.start = info.start + len
                //没有主键会插入多条导致数据错乱
                FewwindDB.daoDB.getDao().insertDownInfo(info)
                len = bis.read(buffer)
            }
            Logger.v(" ${len}  <> ${info.start}")
        } catch (e: Exception) {
            Logger.e("e = $e")
        } finally {
            isByte?.close()
            randomAccess.close()
            bis.close()
        }
    }
}