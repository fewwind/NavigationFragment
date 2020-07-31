package com.feng.advance.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.feng.advance.R;
import com.feng.advance.base.BaseFragment;
import com.feng.advance.base.CommonAdapterRV;
import com.feng.advance.base.ViewHolderRV;
import com.feng.advance.bean.TestBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends BaseFragment {

    private RecyclerView mRv;
    private List<TestBean> mDatas = new ArrayList<>();
    ArrayList<RecyclerView.ViewHolder> creatSet = new ArrayList<>();
    ArrayList<RecyclerView.ViewHolder> bindSet = new ArrayList<>();

    public RecyclerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        RecyclerFragment fragment = new RecyclerFragment();
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
        return R.layout.fragment_rv;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        mRv = view.findViewById(R.id.recycler_view);
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        CommonAdapterRV adapterRV = new CommonAdapterRV<TestBean>(getActivity(), mDatas, R.layout.item_rv) {
            @Override
            public void convert(ViewHolderRV holder, final TestBean bean) {
                holder.setText(R.id.manager_history_agree, bean.value + "");
                addIndex(bindSet, holder);
                Logger.e(getIndex(bindSet, holder) + "->bind= " + holder);
            }

            @Override
            public void onViewHolderCreated(ViewHolderRV holder, View itemView, int viewType) {
                super.onViewHolderCreated(holder, itemView, viewType);
                addIndex(creatSet, holder);
                Logger.v(creatSet.size() + "->creat= " + holder);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }
        };
        adapterRV.setHasStableIds(true);
        mRv.setAdapter(adapterRV);
        View fab = view.findViewById(R.id.notify);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> {
            adapterRV.notifyDataSetChanged();
        });
    }

    private int getIndex(ArrayList list, Object o) {

        return list.indexOf(o);
    }

    private <T> void addIndex(ArrayList<T> list, T o) {
        if (list.contains(o)) return;
        list.add(o);
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
        for (int i = 0; i < 22; i++) {
            mDatas.add(new TestBean(i, i));
        }
    }

//    public static final String VP_FRAG = "ViewPager_Fragment";
//    public static final String VIEW_FRAG = "view_Fragment";
//    public static final String TOUCH_FRAG = "touch_Fragment";
//    public static final String NET_FRAG = "net_Fragment";
}
