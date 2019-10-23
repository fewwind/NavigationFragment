package com.chaozhuo.parentmanager.test;

import android.content.Context;

import com.chaozhuo.parentmanager.test.simpleplugin.FieldUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by fewwind on 18-12-27.
 */

public class Util {
    public void plugin(Context context) {
        PathClassLoader pathClassLoader = new PathClassLoader("", context.getClassLoader());//只能加载已经安装的apk
        File optimizeFile = context.getFileStreamPath("optimizeFile");
        File pluginFile = new File(context.getExternalCacheDir(), "plugin.apk");
        DexClassLoader pluginClassLoader = new DexClassLoader(pluginFile.getAbsolutePath(), optimizeFile.getAbsolutePath(), null, context.getClassLoader());
        try {
            Object path = FieldUtil.getField(Class.forName(""), pluginClassLoader, "path");
            int[] arr = {1, 2, 9, 15, 63};
            for (int i = 0; i < arr.length; i++) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            feibonaqi(1,2);
        }
        EnumClass a = EnumClass.One;
    }

    private List<Integer> feibonaqi(int x, int y, List<Integer> list) {
        list.add(x + y);
        return list;
    }
    private int feibonaqi(int x, int y) {
        return x+y;
    }
  public void register(Object object){
      Class<?> aClass = object.getClass();
  }
}
