package com.feng.advance.util;

import com.orhanobut.logger.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static CharSequence findUrl(){
        String a = "我http://www.qq.com大法师结束他http://www.cc.com大法师结束";
        //String regex = "(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\&%\\+\\$#_=]*)?";
        String regex = "((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|((www|m).[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(a);
        while (matcher.find()){
            CharSequence linkContent = a.subSequence(matcher.start(), matcher.end());
            Logger.e("ssss = "+linkContent);
        }
        return "";
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
