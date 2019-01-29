package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.util.ArrayMap;

/**
 * Created by fewwind on 19-1-25.
 */

public class FragmentFactory {
    public static ArrayMap<String, Fragment> mFragments = new ArrayMap<>();

    public static Fragment creat(String type) {
        Fragment fragment = mFragments.get(type);
        if (fragment != null) return fragment;
        switch (type) {
            case LearnListFragment.VP_FRAG:
                fragment = ManagerFragment.newInstance();
                break;
            case LearnListFragment.TOUCH_FRAG:
                fragment = TouchFragment.newInstance();
                break;
            case LearnListFragment.VIEW_FRAG:
                fragment = ManagerFragment.newInstance();
                break;
        }
        mFragments.put(type, fragment);
        return fragment;
    }
}
