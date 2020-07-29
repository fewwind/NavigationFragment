package com.chaozhuo.parentmanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.chaozhuo.parentmanager.base.CommonAdapterRV;
import com.chaozhuo.parentmanager.base.ViewHolderRV;
import com.chaozhuo.parentmanager.bean.FragmentBean;
import com.chaozhuo.parentmanager.test.kotlin.KotlinTest;
import java.util.ArrayList;
import java.util.List;

public class LearnListFragment extends BaseFragment {

    private RecyclerView mRv;
    private List<FragmentBean> mDatas = new ArrayList<>();
    KotlinTest kotlinTest;
    public LearnListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        LearnListFragment fragment = new LearnListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        makeList();
    }

    @Override
    protected int getContentViewLayoutID() {
        kotlinTest = new KotlinTest("");
        return R.layout.fragment_list;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        mRv = view.findViewById(R.id.recycler_view);
        mRv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        CommonAdapterRV adapterRV = new CommonAdapterRV<FragmentBean>(getActivity(), mDatas, R.layout.item_apply_history) {
            @Override
            public void convert(ViewHolderRV holder, final FragmentBean bean) {
                holder.setText(R.id.manager_history_agree, bean.fragment.getSimpleName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) mListener.click(bean.fragment);
                        kotlinTest.lambda();
                    }
                });
            }
        };
        mRv.setAdapter(adapterRV);
        mRv.setNestedScrollingEnabled(false);
    }

    public IFragClick mListener;

    public interface IFragClick {
        void click(Class type);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragClick) {
            mListener = (IFragClick) context;
        }
    }

    private void makeList() {
        mDatas.add(new FragmentBean(ViewFragment.class));
        mDatas.add(new FragmentBean(TouchFragment.class));
        mDatas.add(new FragmentBean(NetFragment.class));
        mDatas.add(new FragmentBean(SortFragment.class));
        mDatas.add(new FragmentBean(RecyclerFragment.class));
        mDatas.add(new FragmentBean(ScrollFragment.class));
    }

//    public static final String VP_FRAG = "ViewPager_Fragment";
//    public static final String VIEW_FRAG = "view_Fragment";
//    public static final String TOUCH_FRAG = "touch_Fragment";
//    public static final String NET_FRAG = "net_Fragment";
}
