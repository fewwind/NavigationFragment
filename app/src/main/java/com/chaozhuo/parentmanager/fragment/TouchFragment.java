package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

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
        final View cview = view.findViewById(R.id.view_c);
//        cview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Logger.e("viewC Click");
//            }
//        });
        Logger.w("Size = " + cview.getMeasuredWidth());
        cview.post(new Runnable() {
            @Override
            public void run() {
                Logger.w("Handler = " + cview.getMeasuredWidth());
            }
        });
        cview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Logger.w("ViewTree = " + cview.getMeasuredWidth());
                cview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        cview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Logger.e("touListener = " + event.toString());
                return false;
            }
        });
        cview.setOnClickListener(v -> {
            cview.requestLayout();
        });
        //if(down或者mFirstTouchTarget不为null)，才会调用onIntercept
        // - 不拦截的话if（Down）中遍历子View寻找可以处理事件的child，倒叙遍历，如果不在触摸范围或者不在动画直接contniue，调用dispatchTransformedTouchEvent
        // 如果返回值为true，则addTouchTarget给mFirstTouchTarget赋值。
        // dispatchTouchevent不论是VP或者View，如果返回false，则回调父View的onTouchevent方法
        // 如果down的父view没有拦截，分发给子View处理，则mTirstTouchTarget不为null，move的时候拦截了，则会发送cancle事件给子view，并把touchTarget置为null。
    }

/*    private boolean dispatchTouchEvent() {
        boolean isHand = false;
        if (onInterceptTouchEvent()) {
            isHand = super.dispatchTouchEvent();// super就是View源码，默认调用onTouchEvent();
        } else {
            isHand = child.dispatchTouchEvent();
        }
        return isHand;
    }*/

}
