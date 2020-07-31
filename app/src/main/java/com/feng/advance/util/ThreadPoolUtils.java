
package com.feng.advance.util;


import android.os.AsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Description:  线程池辅助类，整个应用程序就只有一个线程池去管理线程。 可以设置核心线程数、最大线程数、额外线程空状态生存时间，阻塞队列长度来优化线程池。
 * 下面的数据都是参考Android的AsynTask里的数据。
 */
public class ThreadPoolUtils {

    // 线程池
    private static Executor threadPool;

    static {
        threadPool = AsyncTask.THREAD_POOL_EXECUTOR;
    }

    public static void execute(Runnable runnable) {
        threadPool.execute(runnable);
    }

    public static void submit(Runnable runnable) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 0, 1, TimeUnit.HOURS, new LinkedBlockingDeque<Runnable>());
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        Future<?> submit = executor.submit(task);
    }

    public static Executor getExecutor() {
        return threadPool;
    }

}