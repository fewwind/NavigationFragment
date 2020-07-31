package com.feng.advance.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.feng.advance.R;
import com.feng.advance.base.BaseFragment;
import com.feng.advance.base.CommonAdapterRV;
import com.feng.advance.base.HeaderWrapperAdapter;
import com.feng.advance.base.ViewHolderRV;
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
        final CommonAdapterRV adapterRV = new CommonAdapterRV<String>(getActivity(), mDatas, R.layout.item_apply_history) {
            @Override
            public void convert(ViewHolderRV holder, String s) {
                holder.setText(R.id.manager_history_agree, s);
                Logger.v("BindHolder--> " + s);
            }
        };
//        mAdapter = new HeaderWrapperAdapter(adapterRV);
        mRv.setAdapter(adapterRV);
        mRv.setNestedScrollingEnabled(false);
        mRv.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mRv.setY(100);
            }
        }, 200);
        view.findViewById(R.id.notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterRV.notifyDataSetChanged();
            }
        });
    }

}
