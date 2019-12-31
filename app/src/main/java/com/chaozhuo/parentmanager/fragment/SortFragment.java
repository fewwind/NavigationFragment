package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SortFragment extends BaseFragment {

    public SortFragment() {
        // Required empty public constructor
    }

    int[] array = {2, 9, 4, 6, 0, 8, 14, 7};

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
//        new SuanFa().test();
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
                if (arr[minIndex] > arr[j]) {
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
        for (int i = 0; i < arr.length; i++) {
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
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {//注意第二重循环的条件
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    private int removeDuplicates(int[] arr) {//有序数组去重
        int[] array = {0, 1, 2, 2, 5, 6, 6, 8};
        int i = 0;
        int j;
        for (j = 1; j < arr.length; j++) {
            if (arr[j] != arr[i]) {
                arr[++i] = arr[j];
            }
        }
        //j=1 -A1=1 i=1
        //j=2 -A2=2 i=2
        //j=3
        //j=4 -A3=5 i=3
        //j=5 -A4=6 i=4
        //j=6
        //j=7 -A5=8 i=5
        return i + 1;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // 单链表反转
    private void reverseLink(Node head) {
        Node next = null;
        Node pre = null;
        // 每次循环head都在改变，一次是链表的node，第一次node1，第二次node2 ...
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }

    void rev(Node head) {
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }

    //判断链表是否有环
    private boolean isNodeCycle(Node node) {
        Set<Node> set = new HashSet<>();
        while (node != null) {
            if (set.contains(node)) {
                return true;
            } else {
                set.add(node);
                node = node.next;
            }
        }
        return false;
    }

    //判断链表是否有环
    private boolean isNodeCycle2(Node node) {
        Node quick = node;
        Node slow = node;
        // 为什么是quick的循环，因为只要有环必定永不为空，while会一直循环，必能能找到快慢相等的点
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow) return true;
        }
        return false;
    }

    private int get(int n) {
        if (n <= 2) return 1;
        return get(n - 1) + get(n - 2);
    }


    public long fibonacci(int n) {
        // 当输入非正整数的时候返回0
        if (n <= 0) {
            return 0;
        }
        // 输入1或者2的时候返回1
        if (n == 1 || n == 2) {
            return 1;
        }
        // 第n-2个的Fibonacci数的值
        long prePre = 1;
        // 第n-1个的Fibonacci数的值
        long pre = 1;
        // 第n个的Fibonacci数的值
        long current = 2;
        // 求解第n个的Fibonacci数的值
        for (int i = 3; i <= n; i++) {
            // 求第i个的Fibonacci数的值
            current = prePre + pre;
            // 更新记录的结果，prePre原先记录第i-2个Fibonacci数的值
            // 现在记录第i-1个Fibonacci数的值
            prePre = pre;
            // 更新记录的结果，pre原先记录第i-1个Fibonacci数的值
            // 现在记录第i个Fibonacci数的值
            pre = current;
        }
        // 返回所求的结果
        return current;
    }

    String s = "ABCDEFGHIJKLMNOPQRESUVWXYZ";
    volatile LinkedList<Character> list = new LinkedList<>();

    private void threadSortCycle() {
        for (int i = 0; i < s.length(); i++) {
            list.add(s.charAt(i));
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (!list.isEmpty()) print(1);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (!list.isEmpty()) print(2);
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (!list.isEmpty()) print(3);
            }
        }.start();
    }

    private synchronized void print(int id) {
        if (list.isEmpty()) return;
        Character character = list.get(0);
        int i = s.indexOf(String.valueOf(character)) % 3 + 1;
        if (i == id) {
            Logger.e(id + "==" + list.removeFirst());
        }
    }

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
