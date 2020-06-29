package com.chaozhuo.parentmanager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.chaozhuo.parentmanager.base.ViewHolderRV;
import com.chaozhuo.parentmanager.util.DataUtil;
import com.chaozhuo.parentmanager.weight.adapter.HallSize;
import com.chaozhuo.parentmanager.weight.table.ChatHallInfoView2;
import com.chaozhuo.parentmanager.weight.table.GameHallBg;
import com.shuo.ruzuo.chat.adapter.HallLayoutAdapter;
import org.jetbrains.annotations.NotNull;

@Route(path = "/main/view")
public class ViewFragment extends BaseFragment {

    public ViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        ViewFragment fragment = new ViewFragment();
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
        return R.layout.fragment_manager;
    }
    ChatHallInfoView2 hallView;
    HallLayoutAdapter adapter;
    @Override
    protected void initViewsAndEvents(View view) {
        hallView = view.findViewById(R.id.hall_view);
        GameHallBg hallBg = new GameHallBg(getActivity(),null);
        hallView.setHallBg(HallSize.Companion.creat(), hallBg);
        hallView.setAdapter(new HallLayoutAdapter<String>(getActivity(), DataUtil.creatSimlpe(6),R.layout.view_broadcast_info) {
            @Override public void converHolder(@NotNull ViewHolderRV holder, String o) {

            }
        });
    }

}
