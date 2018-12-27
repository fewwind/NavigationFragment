package com.chaozhuo.parentmanager.test.simpleplugin;

import android.content.Context;

import com.chaozhuo.common.BaseApp;
import com.chaozhuo.parentmanager.util.FileUtils;
import com.orhanobut.logger.Logger;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * Created by fewwind on 18-12-27.
 */

public class LoadPlugin {
    public static Context context = BaseApp.app;

    public static void load() {
        final String apkPath = context.getFilesDir().getPath() + File.separator + "plugin.apk";
        final String dexPath = context.getFilesDir().getPath() + File.separator + "dex";
        FileUtils.getInstance(context).copyAssetsToSD("", context.getFilesDir().getPath()).setFileOperateCallback(new FileUtils.FileOperateCallback() {
            @Override
            public void onSuccess() {
                try {
                    if (!new File(dexPath).exists()){
                        new File(dexPath).mkdirs();
                    }
                    DexClassLoader classLoader = new DexClassLoader(apkPath,dexPath,null,context.getClassLoader());
                    Class<?> aClass = classLoader.loadClass("com.chaozhuo.login.LoginBean");
//                    Object bean = aClass.newInstance();
//                    Method getLoginInfo = aClass.getDeclaredMethod("getLoginInfo");
//                    getLoginInfo.setAccessible(true);
//                    Object invoke = getLoginInfo.invoke(bean);

                    ILoadBean bean = (ILoadBean) aClass.newInstance();
                    Logger.i("invoke success = "+bean.getLoginInfo());
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.e("Ex = "+e);
                }
            }

            @Override
            public void onFailed(String error) {

            }
        });

    }
}
