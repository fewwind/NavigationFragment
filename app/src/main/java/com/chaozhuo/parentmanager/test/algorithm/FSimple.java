package com.chaozhuo.parentmanager.test.algorithm;

import com.orhanobut.logger.Logger;

public class FSimple {
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

    void minusLeft() {
        int[] arr = {2, -1, 3, -2, 4, -3};
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
        int[] arr = {2, -1, 3, -2, 4, -3};
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
}
