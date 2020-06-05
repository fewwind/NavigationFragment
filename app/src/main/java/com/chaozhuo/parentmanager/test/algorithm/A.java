package com.chaozhuo.parentmanager.test.algorithm;

import com.chaozhuo.parentmanager.bean.LinkNode;

public class A {
    void revaser(LinkNode flink){
        int [] arr1 = null;
        int [] arr2 = null;
        int a=0,b=0,c=0;
        int[] res = new int[arr1.length+arr2.length];
        while (a<arr1.length && b<arr2.length){
            if (arr1[a]<arr2[b]){
                res[c++] = arr1[a];
                a++;
            }else {
                res[c++] = arr1[b];
                b++;
            }
        }
        while (a<arr1.length){
            res[c++] = arr1[a++];
        }
    }
    void test(int[]arr,int low,int high){
        if (low>high) return;
        int i = low;
        int j = high;
        int t = arr[low];
        while (i<j){
            while (arr[j]>t) j--;
        }
        arr[low] = arr[j];
        arr[j] = t;
        test(arr,low,j-1);
        test(arr,j+1,high);
    }
}
