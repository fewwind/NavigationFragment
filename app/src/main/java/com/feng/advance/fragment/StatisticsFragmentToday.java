package com.feng.advance.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.feng.advance.R;
import com.feng.advance.base.BaseRefreshFragment;
import com.feng.advance.base.CommonAdapterRV;
import com.feng.advance.base.HeaderWrapperAdapter;
import com.feng.advance.base.ViewHolderRV;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragmentToday extends BaseRefreshFragment {

    private RecyclerView mRv;
    private HeaderWrapperAdapter mAdapter;
    private List<String> mDatas = new ArrayList<>();

    public StatisticsFragmentToday() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        StatisticsFragmentToday fragment = new StatisticsFragmentToday();
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
//            mDatas.add("==" + i);
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
        CommonAdapterRV adapterRV = new CommonAdapterRV<String>(getActivity(), mDatas, android.R.layout.activity_list_item) {
            @Override
            public void convert(ViewHolderRV holder, String s) {
                holder.setText(android.R.id.text1, s);
            }
        };
        mAdapter = new HeaderWrapperAdapter(adapterRV);

        mAdapter.addHeaderView(mEmptyView);
        mRv.setAdapter(mAdapter);
        mRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    int count = 0;

    @Override
    public void onRefresh() {
        for (int i = 0; i < 1; i++) {
            mDatas.add(0, "new  =" + count);
        }
        count++;
        TextView tv = new TextView(getActivity());
        tv.setText("I am Header");
        mAdapter.removeHeaderView(mEmptyView);
        mAdapter.addHeaderView(tv);
        mAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }
}
