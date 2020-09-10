package com.feng.advance.copy2creat.thread;

import android.os.SystemClock;
import com.orhanobut.logger.Logger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class FThreadPool {
    //https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html
    private LinkedBlockingQueue<Runnable> list;
    private Set<Thread> set = new HashSet<>();

    private FThreadPool() {
        CountDownLatch latch = new CountDownLatch(1);
        //latch.await();
        //wait 和 条件锁await（参考同步队列）都会释放锁，await就是利用lockSupport(它的park方法本身不释放锁)
        //LockSupport优势唤醒指定线程。notify唤醒是随机的
        // 同步队列的take方法先获取锁，然后while（get==null）,调用条件锁等待,此时释放锁，等待被唤醒
        //非公平锁和公平锁区别，非公平锁获取锁的时候直接尝试获取，公平锁先判断是否有等待队列
    }

    private static volatile FThreadPool pool;

    public static FThreadPool creat() {
        if (pool == null) {
            synchronized (FThreadPool.class) {
                if (pool == null) pool = new FThreadPool(2);
            }
        }
        return pool;
    }


    public FThreadPool(int count) {
        this.list = new LinkedBlockingQueue<>();
        for (int i = 0; i < count; i++) {
            WorkThread thread = new WorkThread();
            thread.setName("T-" + i);
            thread.start();
            set.add(thread);
        }
    }


    public void submit(Runnable runnable) {
        Logger.i("submit");
        list.offer(runnable);
    }


    public void test() {
        FutureTask task = new FutureTask<String>(new Runnable() {
            @Override public void run() {
                Logger.v(Thread.currentThread().getName() + ">start");
                SystemClock.sleep(4000);
            }
        }, "");
        submit(task);
    }


    class WorkThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                SystemClock.sleep(1000);
                Logger.d("start run");
                Runnable pop = null;
                try {
                    pop = list.take();
                    pop.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
