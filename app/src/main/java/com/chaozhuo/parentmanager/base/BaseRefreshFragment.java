package com.chaozhuo.parentmanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.chaozhuo.parentmanager.R;

/**
 * Created by fewwind on 18-12-4.
 */

public abstract class BaseRefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected SwipeRefreshLayout mRefreshLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(this);
    }
}
