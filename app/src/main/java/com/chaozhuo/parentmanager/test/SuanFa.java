package com.chaozhuo.parentmanager.test;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SuanFa {
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
                        if (count == 0) {
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

    int deep(View view) {
        if (!(view instanceof ViewGroup)) {
            return 0;
        }
        ViewGroup group = (ViewGroup) view;
        if (group.getChildCount() == 0) return 0;
        int result = 0;
        for (int i = 0; i < group.getChildCount(); i++) {
            View v = group.getChildAt(i);
            int deep = deep(v) + 1;
            result = Math.max(deep, result);
        }
        return result;
    }

    class Solution {
        public int maxDepth(TreeNode root) {
            int max = 0;//存储子树的深度
            int leftMax = 0;
            int rightMax = 0;
            if (root != null) {//如果当前子树不为空,非空树
                max++;//深度加1
                leftMax = maxDepth(root.left);//左子树深度
                rightMax = maxDepth(root.right);//右子树深度
                max += leftMax >= rightMax ? leftMax : rightMax;//当前子树的深度
            }
            return max;//返回当前子树的深度

        }
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
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
