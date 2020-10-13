package com.feng.advance.bean;

/**
 * Created by fewwind on 19-1-25.
 */

public class FragmentBean <T extends Class>{
    public String name;
    public int type;
    public T fragment;

    public FragmentBean(T fragment) {
        this.fragment = fragment;
        name = fragment.getSimpleName().replace("Fragment","");
    }

    public FragmentBean(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public FragmentBean(String name) {
        this.name = name;
    }
}
