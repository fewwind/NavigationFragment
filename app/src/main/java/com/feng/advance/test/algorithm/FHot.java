package com.feng.advance.test.algorithm;

import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

public class FHot {
    //https://zhuanlan.zhihu.com/p/84230862
    static int n = 3;
    public static int[][] arr = new int[n][n];


    static {
        int a = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = a++;
            }
        }
    }


    public static void printArr(int[][] arr) {
        int n = arr.length;
        int m = 0;
        while (m < n) {
            if (m % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    int q = arr[m][j];
                    Logger.v("q = " + q);
                }
                m++;
            } else {
                for (int j = n - 1; j > -1; j--) {
                    int q = arr[m][j];
                    Logger.e("q = " + q);
                }
                m++;
            }
        }
    }


    //连续子数组最大和
    public static int maxSum() {
        int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int n = arr[0];
        int sum = arr[0];
        if (arr.length == 1) return arr[0];
        for (int i = 1; i < arr.length; i++) {
            n = Math.max(arr[i], arr[i] + n);
            sum = Math.max(n, sum);
        }
        Logger.v("sun = " + sum);
        return sum;
    }


    //二进制中不相等的位数 比如 结果：2
    // 1   (0 0 0 1)
    //4    (0 1 0 0)
    public void minghanJuLi(int x, int y) {
        int a = 0;
        while (x != 0 || y != 0) {
            a += (x % 2) ^ (y % 2);
            x >>= 1;
            y >>= 1;
            // 1 2 2 4 5
        }
    }

    //[4,3,2,7,8,2,3,1]
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

}
