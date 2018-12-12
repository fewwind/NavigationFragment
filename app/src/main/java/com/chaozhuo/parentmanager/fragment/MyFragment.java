package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.chaozhuo.parentmanager.base.CommonAdapterRV;
import com.chaozhuo.parentmanager.base.HeaderWrapperAdapter;
import com.chaozhuo.parentmanager.base.ViewHolderRV;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends BaseFragment {

    private RecyclerView mRv;
    private HeaderWrapperAdapter mAdapter;
    private List<String> mDatas = new ArrayList<>();

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
            for (int i = 0; i < 50; i++) {
                mDatas.add("new  =" + i);
            }
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        mRv = view.findViewById(R.id.recycler_view);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        CommonAdapterRV adapterRV = new CommonAdapterRV<String>(getActivity(), mDatas, R.layout.item_apply_history) {
            @Override
            public void convert(ViewHolderRV holder, String s) {
//                holder.setText(android.R.id.text1, s);
            }
        };
        Logger.v("initVIew");
        mAdapter = new HeaderWrapperAdapter(adapterRV);
        mRv.setAdapter(mAdapter);
        mRv.setNestedScrollingEnabled(false);
        mRv.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mRv.setY(100);
            }
        }, 200);
    }

}
