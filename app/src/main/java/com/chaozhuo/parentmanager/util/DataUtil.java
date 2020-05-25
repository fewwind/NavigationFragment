package com.chaozhuo.parentmanager.util;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    public static List<String> creatSimlpe(int count){
        List<String> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            data.add(i+"");
        }
        return data;
    }
}
