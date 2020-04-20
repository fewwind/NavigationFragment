package com.chaozhuo.parentmanager.test.picasso;

public class Test {
    public void test(int[] arr1,int[]arr2){
        for (int i = 0; i < arr1.length-1; i++) {
            for (int j = 0; j < arr1.length-1-i; j++) {
                if (arr1[j]>arr1[j+1]);
            }
            int index = i;
            for (int j = i+1; j < arr1.length - 1; j++) {
                if (arr1[index]>arr1[j]) index = i;
            }
        }
    }

}
