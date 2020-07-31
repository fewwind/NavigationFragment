package com.feng.advance.fragment;

import android.support.v4.app.Fragment;
import android.util.ArrayMap;

/**
 * Created by fewwind on 19-1-25.
 */

public class FragmentFactory {
    public static ArrayMap<String, Fragment> mFragments = new ArrayMap<>();

    public static Fragment creat(Class<? extends Fragment> type) {
        Fragment fragment = mFragments.get(type.getSimpleName());
        if (fragment != null) return fragment;
        try {
            fragment = type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mFragments.put(type.getSimpleName(), fragment);
        return fragment;
    }

//    public static <T extends Fragment> Fragment creat(Class<T> type) {
//        T fragment = (T) mFragments.get(type.getSimpleName());
//        if (fragment != null) return fragment;
//        try {
//            fragment = type.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mFragments.put(type.getSimpleName(), fragment);
//        return fragment;
//    }
}
