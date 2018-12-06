package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;

public class ManagerFragment extends BaseFragment {

    public ManagerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        ManagerFragment fragment = new ManagerFragment();
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
        return R.layout.fragment_manager;
    }

    @Override
    protected void initViewsAndEvents(View view) {

    }

}
