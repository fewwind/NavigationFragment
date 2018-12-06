package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.chaozhuo.parentmanager.base.FragmentPagerAdapter;

public class StatisticsFragment extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private String[] mTitles;
    private FragmentPagerAdapter mAdapter;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        StatisticsFragment fragment = new StatisticsFragment();
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
        return R.layout.fragment_statistics;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        mTitles = getResources().getStringArray(R.array.tab_title);
        mViewPager = view.findViewById(R.id.statistics_vp);
        mTabLayout = view.findViewById(R.id.statistics_tab);
        mAdapter = new Adapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class Adapter extends FragmentPagerAdapter {
        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return StatisticsFragmentToday.newInstance();
            } else if (position == 1) {
                return StatisticsFragmentWeek.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}
