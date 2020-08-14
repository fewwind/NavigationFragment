package com.feng.advance.util;

public class StringUtil {
    public static float stringLegth(String value) {
        float valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 1;
            } else {
                valueLength += 0.5;
            }
        }
        return valueLength;
    }

    public static int stringMaxIndex(String value, int max) {
        float valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 1;
            } else {
                valueLength += 0.5;
            }
            if (valueLength >= max) return i;
        }
        return -1;
    }
}
