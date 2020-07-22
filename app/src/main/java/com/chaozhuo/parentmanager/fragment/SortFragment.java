package com.chaozhuo.parentmanager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SortFragment extends BaseFragment {

    public SortFragment() {
        // Required empty public constructor
    }


    int[] array = { 2, 9, 4, 6, 0, 8, 14, 7 };


    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        SortFragment fragment = new SortFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_touch_event;
    }


    @Override
    protected void initViewsAndEvents(View view) {
        View cview = view.findViewById(R.id.view_c);
        cview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Logger.e("viewC Click");

            }
        });
//        threadSortCycle();
//        lengthOfLongest2("abcabcbb");
//        lengthOfLongest2("arabcacfr");
//        new FThread().test();
    }


    static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;

        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            final int midVal = array[mid];

            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }


    private void select(int[] arr) {
        //排序
        //大循环。循环n-1次
        for (int i = 0; i < arr.length - 1; i++) {
            //假设第i个元素最小
            int minIndex = i;
            //i+1开始，依次用最小的比较
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            //第一趟比较完，比较最小值索引是不是第i个
            if (minIndex != i) {
                int temp;
                temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
        Logger.w("select = " + Arrays.toString(arr));
    }


    void select2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int tem = arr[i];
                arr[i] = arr[min];
                arr[min] = tem;
            }
        }
        Logger.w("select2 = " + Arrays.toString(arr));
    }


    private void bubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    public void bubbleSort(int[] a) {
        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {//注意第二重循环的条件
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }


    private int get(int n) {
        if (n <= 2) return 1;
        return get(n - 1) + get(n - 2);
    }


    public static long fibonacci(int n) {
        // 当输入非正整数的时候返回0
        if (n <= 0) {
            return 0;
        }
        long current = 0;
        long next = 1;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum = current + next;
            System.out.println("curr " + current + " -next " + next);
            current = next;
            next = sum;
        }
        return current;
    }


    String s = "ABCDEFGHIJKLMNOPQRESUVWXYZ";


    private void lengthOfLongest(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            set.clear();
            for (int j = i; j < s.length(); j++) {
                char charAt = s.charAt(j);
                if (set.contains(charAt)) {
                    max = Math.max(max, set.size());
                    break;
                } else {
                    set.add(charAt);
                }
            }
        }
        Logger.w("最长字符串 - " + max);
    }


    private void lengthOfLongest2(String s) {
        //arabcacfr
        List<Character> list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (list.contains(character)) {
                max = Math.max(list.size(), max);
                list = list.subList(list.indexOf(character) + 1, list.size());
                list.add(character);
            } else {
                list.add(character);
                max = Math.max(list.size(), max);
            }

        }
        Logger.w(list + "最长字符串 - " + max);
    }


    public static void longestSub(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            } else {
                set.add(s.charAt(right++));
            }
            max = Math.max(max, set.size());
        }
    }


    void su(int x) {
        int l = (int) Math.sqrt(x);
        for (int i = 0; i <= l; i++) {
            if (x % i == 0) ;
        }
    }


    private int findK(int[] arr, int k, int low, int high) {
        int l = low;
        int h = high;
        int tmp = arr[low];
        while (l < h) {
            // tmp大于高位值再减减，就是保留小值，把大的移到前排，逆序
            while (l < h && tmp >= arr[h]) {
                h--;
            }
            arr[l] = arr[h];
            while (l < h && tmp <= arr[l]) {
                l++;
            }
            arr[h] = arr[l];
        }
        arr[h] = tmp;
        if (h == k - 1) {
            return h;
        } else if (h > k - 1) {
            return findK(arr, k, low, h - 1);
        } else {
            return findK(arr, k, h + 1, high);
        }
    }


    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            Logger.d("i = " + i + " j = " + j + " low = " + low);
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
            Logger.i(Arrays.toString(arr));
        }
        Logger.e("i = " + i + " j = " + j + " low = " + low);
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }

}
