package com.chaozhuo.parentmanager.test.algorithm;

import android.os.SystemClock;

import com.orhanobut.logger.Logger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class FThread {
    // 有序数组合并，链表合并
    AtomicInteger integer = new AtomicInteger(1);
    static int count = 10;

    public void test() {
//        sortThread();
        consumer();
    }


    public void consumer() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (integer) {
                        while (count >= 20) {
                            try {
                                integer.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        SystemClock.sleep(new Random().nextInt(500));
                        count++;
                        Logger.e("生产 - " + count);
                        integer.notifyAll();
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (integer) {
                        while (count == 0) {
                            try {
                                integer.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        SystemClock.sleep(new Random().nextInt(200));
                        count--;
                        Logger.i("消费 - " + count);
                        integer.notifyAll();
                    }
                }
            }
        }.start();

    }

    public void sortThread() {
        new Thread() {
            @Override
            public void run() {
                while (integer.get() < count) {
                    printThread(1);
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (integer.get() < count) {
                    printThread(2);
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (integer.get() < count) {
                    printThread(0);
                }
            }
        }.start();
    }

    private void printThread(int id) {
        synchronized (integer) {
            if (integer.get() % 3 == id) {
                SystemClock.sleep(200);
                Logger.w(integer.get() + "->Thread Id = " + id);
                integer.incrementAndGet();
                integer.notifyAll();
            } else {
                try {
                    integer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Integer moreThanHalfNum(int[] array) {
        if (array == null)
            return null;
        Integer number = null;
        int count = 0;
        Integer resultInteger = null;
        for (int i = 0; i < array.length; i++) {
            if (number == null) {
                number = array[i];
                count++;
            } else {
                if (array[i] != number)
                    if (count == 0) {
                        number = array[i];
                        count = 1;
                    } else
                        count--;
                else
                    count++;
            }
            if (count == 1)
                resultInteger = number;
        }
        return resultInteger;
    }

    class product extends Thread {
        AtomicInteger food;

        public product(AtomicInteger food) {
            this.food = food;
        }

        @Override
        public void run() {
            super.run();
            make();
        }

        public void make() {
            while (true) {
                synchronized (food) {
                    // 生产者消费者关键是达到临界值后暂停
                    while (food.get() == 3) {
                        try {
                            food.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    SystemClock.sleep(new Random().nextInt(1000));
                    int i = food.incrementAndGet();
                    Logger.i("Make = " + i);
                    food.notifyAll();
                }
            }
        }
    }

}
