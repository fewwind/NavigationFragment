package com.chaozhuo.parentmanager.test.offer;

public class String2Int {
    public int toInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int index = 0;
        int result = 0;
        boolean flag = true;
        if (s.charAt(0) == '+') {
            index++;
            flag = true;
        } else if (s.charAt(0) == '-') {
            index++;
            flag = false;
        }
        while (index < s.length()) {
            char num = s.charAt(index++);
            if (num >= '0' && num <= '9') {
                result = result * 10 + num;
            } else return 0;
        }
        return flag ? result : -result;
    }

    void s2i(String s) {
        if (s == null || s.length() == 0) return;
        int index = 0;
        boolean isFu;
        int resut = 0;
        if (s.charAt(0) == '-') {
            isFu = true;
            index++;
        } else if (s.charAt(0) == '+') {
            isFu = false;
            index++;
        }
        while (index < s.length()) {
            char val = s.charAt(index++);
            if (val <= '9' && val >= '0') {
                resut = resut * 10 + val;
            }else {
                return;
            }
        }

    }


}
