package com.chaozhuo.parentmanager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.MenuItem;
import android.view.View;

import com.chaozhuo.parentmanager.fragment.ManagerFragment;
import com.chaozhuo.parentmanager.fragment.MyFragment;
import com.chaozhuo.parentmanager.fragment.StatisticsFragment;
import com.chaozhuo.utils.ui.ToastUtils;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    public static final String STATISTICS_FRAGMENT = "statistics_fragment";
    public static final String MANAGER_FRAGMENT = "manager_fragment";
    public static final String MY_FRAGMENT = "my_fragment";

    private ContentFragmentFactory mFactory;
    private Toolbar mToolBar;
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCollLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mFactory == null) mFactory = new ContentFragmentFactory();
        mToolBar = findViewById(R.id.tool_bar);
        mAppBar = findViewById(R.id.appbar);
        mCollLayout = findViewById(R.id.collbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.main_container, mFactory.getFragment(STATISTICS_FRAGMENT), STATISTICS_FRAGMENT).commitAllowingStateLoss();
            mCurTag = STATISTICS_FRAGMENT;
        } else {
            mCurTag = savedInstanceState.getString("TAG");
        }
        initToolBar(mCurTag);
        mCollLayout.getBackground().setAlpha(0);
        mToolBar.getBackground().setAlpha(0);
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                Logger.v(" appBar = " + i);
                float alpha = Math.abs(i) / 300f;
                alpha = Math.min(alpha, 1.0f);
                mCollLayout.getBackground().setAlpha((int) (255 * alpha));
                mToolBar.getBackground().setAlpha((int) (255 * alpha));
            }
        });
        mCollLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                Logger.w("attatch");
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                Logger.e("detach");
            }
        });
    }

    private void initToolBar(String mCurTag) {
//        mToolBar.setVisibility(mCurTag.equals(STATISTICS_FRAGMENT) ? View.GONE : View.VISIBLE);
//        mToolBar.setTitle(mCurTag);
    }

    private String mCurTag;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!TextUtils.isEmpty(mCurTag)) {
            outState.putString("TAG", mCurTag);
        }
        super.onSaveInstanceState(outState);
    }

    private void switchFragmentByTag(String tag) {
        if (tag.equals(mCurTag)) return;
        initToolBar(tag);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragmentByTag = fm.findFragmentByTag(tag);
        Fragment fragmentCur = fm.findFragmentByTag(mCurTag);

        if (fragmentCur != null) transaction.hide(fragmentCur);
        if (fragmentByTag == null) {
            fragmentByTag = mFactory.getFragment(tag);
        }
        if (fragmentByTag.isAdded()) {
            transaction.show(fragmentByTag);
        } else {
            transaction.add(R.id.main_container, fragmentByTag, tag);
        }
        transaction.commitAllowingStateLoss();
        mCurTag = tag;
    }

    private static class ContentFragmentFactory {
        ArrayMap<String, Fragment> mFragmentPool = new ArrayMap<>();

        Fragment getFragment(String tag) {
            if (mFragmentPool.get(tag) != null) {
                return mFragmentPool.get(tag);
            }
            Fragment f = createFragment(tag);
            mFragmentPool.put(tag, f);
            return f;
        }

        public Fragment createFragment(String tag) {
            if (tag.equals(STATISTICS_FRAGMENT)) {
                return StatisticsFragment.newInstance();
            } else if (tag.equals(MANAGER_FRAGMENT)) {
                return ManagerFragment.newInstance();
            } else if (tag.equals(MY_FRAGMENT)) {
                return MyFragment.newInstance();
            }
            throw new IllegalArgumentException(String.format("Invalid id %s", tag));
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragmentByTag(STATISTICS_FRAGMENT);
                    return true;
                case R.id.navigation_dashboard:
                    switchFragmentByTag(MANAGER_FRAGMENT);
                    return true;
                case R.id.navigation_notifications:
                    switchFragmentByTag(MY_FRAGMENT);
                    return true;
            }
            return false;
        }
    };

    public static void openApp(Context ctx, String pkg) {
        try {
            Intent launch = ctx.getPackageManager().getLaunchIntentForPackage(pkg);
            launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(launch);
        } catch (Exception e) {
            ToastUtils.showToast(ctx, "打开失败");
            e.printStackTrace();
        }
    }
}