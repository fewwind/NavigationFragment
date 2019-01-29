package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.orhanobut.logger.Logger;

public class TouchFragment extends BaseFragment {

    public TouchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        TouchFragment fragment = new TouchFragment();
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
        return R.layout.fragment_touch_event;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        View cview = view.findViewById(R.id.view_c);
//        cview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Logger.e("viewC Click");
//            }
//        });
        cview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Logger.e("touListener = "+event.toString());
                return false;
            }
        });
    }


}
