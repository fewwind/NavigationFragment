package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;

public class MyFragment extends BaseFragment {


    public MyFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViewsAndEvents(View view) {

    }

}
