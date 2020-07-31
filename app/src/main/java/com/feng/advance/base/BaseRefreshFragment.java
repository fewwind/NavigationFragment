package com.feng.advance.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.feng.advance.R;
import com.feng.advance.weight.EmptyView;

/**
 * Created by fewwind on 18-12-4.
 */

public abstract class BaseRefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout mRefreshLayout;
    protected EmptyView mEmptyView;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(this);
        if (mEmptyView == null) mEmptyView = new EmptyView(view.getContext());
        super.onViewCreated(view, savedInstanceState);
    }
}
