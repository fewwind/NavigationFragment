package com.chaozhuo.parentmanager.copy2creat.thread;

import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class FThreadPool {
    private LinkedList<Runnable> list;
    private Set<Thread> set = new HashSet<>();

    public FThreadPool(int count) {
        this.list = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            WorkThread thread = new WorkThread();
            thread.start();
            set.add(thread);
        }
    }

    public void submit(Runnable runnable) {
        synchronized (list) {
            Logger.i("submit");
            list.add(runnable);
        }
    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                synchronized (list) {
                    if (!list.isEmpty()){
                        Logger.d("start run");
                        Runnable pop = list.pop();
                        pop.run();
                    }
                }
            }
        }
    }
}
