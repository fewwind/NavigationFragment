package com.feng.advance.util.data;

import com.feng.advance.bean.FragmentBean;
import com.feng.advance.fragment.LearnListFragment;
import com.feng.advance.fragment.NetFragment;
import com.feng.advance.fragment.RecyclerFragment;
import com.feng.advance.fragment.RxJavaFragment;
import com.feng.advance.fragment.ScrollFragment;
import com.feng.advance.fragment.SortFragment;
import com.feng.advance.fragment.TouchFragment;
import com.feng.advance.fragment.ViewFragment;
import java.util.ArrayList;
import java.util.List;

public class DataCenter {

    public static List<FragmentBean> mDatas = new ArrayList<>();

    static {
        mDatas.add(new FragmentBean(RxJavaFragment.class));
        mDatas.add(new FragmentBean(LearnListFragment.class));
        mDatas.add(new FragmentBean(ViewFragment.class));
        mDatas.add(new FragmentBean(TouchFragment.class));
        mDatas.add(new FragmentBean(NetFragment.class));
        mDatas.add(new FragmentBean(SortFragment.class));
        mDatas.add(new FragmentBean(RecyclerFragment.class));
        mDatas.add(new FragmentBean(ScrollFragment.class));
    }
}
