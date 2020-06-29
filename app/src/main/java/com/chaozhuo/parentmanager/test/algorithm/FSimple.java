package com.chaozhuo.parentmanager.test.algorithm;

import android.view.View;
import android.view.ViewGroup;
import com.chaozhuo.parentmanager.bean.LinkNode;
import com.orhanobut.logger.Logger;
import java.util.HashMap;
import java.util.Map;

public class FSimple {
    //合并数组
    //单调数列
    public void String2Int() {
        final String s = "192.168.1.1";
        String ss = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                ss += s.charAt(i);
            }
        }
        int resule = 0;
        for (int i = 0; i < ss.length(); i++) {
            // 关键点 不允许使用value方法，int和char互转，char c9 = '9',int int_c9 = c9-'0' ,就是char-‘0’
            double v1 = Integer.valueOf(ss.charAt(i) + "") * (Math.pow(10, ss.length() - i - 1));
            Logger.i(Integer.valueOf(ss.charAt(i) + "") + "vi = " + v1 + "");
            resule += v1;
        }
    }


    public int StrToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int num = 0, index = 0;
        boolean minus = false;
        if (str.charAt(0) == '+') {
            index++;
        } else if (str.charAt(0) == '-') {
            minus = true;
            index++;
        }
        while (index < str.length()) {
            char digit = str.charAt(index++);
            if (digit >= '0' && digit <= '9') {
                num = num * 10 + digit - '0';
            } else {
                return 0;
            }
        }
        Logger.w("v = " + num);
        return minus ? -num : num;
    }


    public int StrToInt2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int num = 0;
        boolean minus = false;
        if (str.charAt(0) == '+') {
            str = str.substring(1);
        } else if (str.charAt(0) == '-') {
            minus = true;
            str = str.substring(1);
        }
        for (int i = 0; i < str.toCharArray().length; i++) {
            char c = str.toCharArray()[i];
            if (c >= '0' && c <= '9') {
                int pow = (int) Math.pow(10, str.toCharArray().length - 1 - i);
                int v = c - '0';
                num += v * pow;
            }
        }
        return minus ? -num : num;
    }


    int StrToInt3() {
        String s = "+1921-6+8";
        if (s.length() == 0) return 0;
        int index = 0;
        boolean minus = false;
        if (s.charAt(0) == '+') {
            index++;
        } else if (s.charAt(0) == '-') {
            index++;
            minus = true;
        }
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && index != 0) {
                continue;
            }
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num += (s.charAt(i) - '0') * Math.pow(10, s.length() - 1 - i);
            }

        }
        return minus ? -num : num;
    }


    void mergeArray(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int[] result = new int[m + n];
        int c = 0;
        int a = 0;
        int b = 0;
        while (a < m && b < n) {
            if (arr1[a] < arr2[b]) {
                result[c++] = arr1[a++];
            } else {
                result[c++] = arr1[b++];
            }
        }
        while (a < m) {
            result[c++] = arr2[a++];
        }
        while (b < n) {
            result[c++] = arr2[b++];
        }
    }


    //有序数组两个数之和为某个值
    public static void better(int[] a, int m) {
        int start = 0;
        int end = a.length - 1;
        while (start < end) {
            if (a[start] + a[end] == m) {
                System.out.println(a[start] + "," + a[end]);
                start++;
                end--;
            } else if (a[start] + a[end] > m) {
                end--;
            } else if (a[start] + a[end] < m) {
                start++;
            }
        }
    }


    void minusLeft() {
        int[] arr = { 2, -1, 3, -2, 4, -3 };
        int tmp = 0;
        int head = -1;
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            if (x < 0) {
                tmp = arr[head + 1];
                arr[++head] = x;
                arr[i] = tmp;
            }
        }
    }


    void minusLeft2() {
        int[] arr = { 2, -1, 3, -2, 4, -3 };
        int head = 0;
        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            if (val < 0) {
                int tmp = arr[head];
                arr[head] = val;
                arr[i] = tmp;
                head++;
            }
        }
    }


    public int reverseNum(int num) {
        int i = 0;
        int res = 0;
        while (num != 0) {
            i = num % 10;
            num /= 10;
            res = res * 10 + i;
        }
        System.out.println("reverseNum = " + res);
        return res;
    }


    public boolean isSquare(int num) {
        return (num & (num - 1)) == 0;
    }


    public boolean isOrderArray(int[] num) {
        //boolean flag = false;此方法无法判断 221这种情况
        //for (int i = 0; i < num.length - 1; i++) {
        //    boolean next = (num[i + 1] - num[i]) >= 0;
        //    if (i > 0) {
        //        if (next != flag) return false;
        //    }
        //    flag = next;
        //}
        boolean isDes = true;
        boolean isIns = true;
        for (int i = 0; i < num.length - 1; i++) {
            if (num[i] - num[i + 1] > 0) isIns = false;
            if (num[i + 1] - num[i] > 0) isDes = false;
        }
        return isIns || isDes;
    }


    public int s2Int(String num) {
        int res = 0;
        for (int i = 0; i < num.length(); i++) {
            res = res * 10 + (num.charAt(i) - '0');
        }
        System.out.println("s2Int = " + res);
        return res;
    }


    public int[] indexAdd(int total, int[] num) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < num.length; i++) {
            map.put(num[i], i);
        }
        for (int i = 0; i < num.length; i++) {
            if (map.get(total - num[i]) != null) {
                res[0] = i;
                res[1] = map.get(total - num[i]);
            }
        }
        System.out.println("res[] = " + res[0] + "-" + res[1]);
        return res;
    }


    public int oneBite(int num) {
        int res = 0;
        while (num != 0) {
            num = num & (num - 1);
            res++;
        }
        return res;
    }


    public int onlyOne(int[] num) {// 数组中只出现一次
        //0 ^ v =v v^v=0 ,结论是相同的数^后为0，只剩下0和单数，结果就是单数
        int res = 0;
        for (int i = 0; i < num.length; i++) {
            res = res ^ num[i];
        }
        return res;
    }


    private int removeDuplicates(int[] arr) {//有序数组去重
        int[] array = { 0, 1, 2, 2, 5, 6, 6, 8 };
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


    //奇数排到偶数前
    public static void order() {
        int[] arr = { 2, 4, 6, 5, 3, 7, };
        int a = 0;
        int end = arr[arr.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                int t = arr[a];
                arr[a] = arr[i];
                arr[i] = t;
                a++;
                if (arr[i] == end) break;
            }
        }
    }
    // aaxbb 只出现一次的字母
    public static char getOnlyOnce(String s){
        int l = 0;
        char[] chars = s.toCharArray();
        int h = s.length()-1;
        while (l<=h){
            int mid = (l+h)/2;
            if (mid ==0 || mid == s.length()-1 || (chars[mid] != chars[mid-1] && chars[mid] !=chars[mid+1])){
                return chars[mid];
            } else if (chars[mid] != chars[mid-1]){
                h = mid-1;
            } else {
                l = mid+1;
            }
        }
        return '0';
    }

    // 先找最左边的index，如果data[mid] == k,我们也将right = mid - 1,
    // 因为我们只要最左边的index，下面找最右边的index也是同理。
    // 也可以二分法呢找到值，然后遍历它前边和后边
    private int valTimes(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (arr[mid] >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int l = left;
        right = arr.length - 1;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (arr[mid] <= k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int r = right;
        return r - l + 1;
    }
    //获取排序数组中出现次数
    public int GetNumberOfK(int [] array , int k) {
        return biSearch(array, k+0.5) - biSearch(array, k-0.5);
    }
    public int biSearch(int [] array, double k){
        int start  = 0, end = array.length - 1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(array[mid] > k){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return start;
    }

    void test(int[] arr, int low, int high) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[index]) index = j;
            }
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
            }
        }
        if (low > high) return;
        int i = low;
        int j = high;
        int base = arr[low];
        while (i < j) {
            while (i < j && base >= arr[j]) {
                j--;
            }
            while (i < j && base >= arr[i]) {
                i--;
            }

        }
        arr[low] = arr[i];
        arr[i] = base;
        int l = 0;
        int h = arr.length - 1;
        int m = l + h / 2;
        int val = 8;
        while (l <= h) {
            if (arr[m] < val) {
                l = m + 1;
            } else {

            }
        }
        LinkNode node1, node2;
        LinkNode head;
        ViewGroup vg = null;
        for (int k = 0; k < vg.getChildCount(); k++) {
            View v = vg.getChildAt(i);

        }
    }

}
