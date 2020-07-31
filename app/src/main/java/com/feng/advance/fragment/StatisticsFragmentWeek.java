package com.feng.advance.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.feng.advance.R;
import com.feng.advance.base.BaseRefreshFragment;
import com.feng.advance.base.DiffAdapter;
import com.feng.advance.base.ViewHolderRV;
import com.feng.advance.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragmentWeek extends BaseRefreshFragment {

    private RecyclerView mRv;
    private DiffAdapter mAdapter;
    private List<TestBean> mDatas = new ArrayList<>();

    public StatisticsFragmentWeek() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        StatisticsFragmentWeek fragment = new StatisticsFragmentWeek();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        for (int i = 0; i < 20; i++) {
            TestBean bean = new TestBean();
            bean.id = i;
            bean.value = i;
            mDatas.add(bean);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_statistics_today;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        mRv = view.findViewById(R.id.recycler_view);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new DiffAdapter<TestBean>(getActivity(), android.R.layout.activity_list_item) {
            @Override
            public void convert(ViewHolderRV holder, TestBean s) {
                holder.setText(android.R.id.text1, "value = " + s.value);
            }
        };

        mRv.setAdapter(mAdapter);
        mAdapter.setData(mDatas);
        mRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    int count = 22;
    @Override
    public void onRefresh() {
        for (int i = 0; i < 1; i++) {
            TestBean bean = new TestBean();
            bean.id = count;
            bean.value = count;
            mDatas.add(0,bean);
            mAdapter.setData(bean);
            count++;
        }

//        mAdapter.setData(mDatas);
        mRefreshLayout.setRefreshing(false);
    }
}
