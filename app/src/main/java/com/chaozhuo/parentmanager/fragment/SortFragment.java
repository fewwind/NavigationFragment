package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.orhanobut.logger.Logger;

public class SortFragment extends BaseFragment {

    public SortFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        SortFragment fragment = new SortFragment();
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
                Logger.e("touListener = " + event.toString());
                return false;
            }
        });
    }

    private int find(int[] array, int value) {
        int min = 0;
        int max = array.length;
        int mid = 0;
        while (min < max) {
            mid = (min + max) / 2;
            if (array[mid] > value) {
                max = mid;
            } else if (array[mid] < value) {
                min = mid;
            } else {
                return mid;
            }
        }
        return ~mid;
    }

    private void select(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            for (int j = index + 1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            if (index != i) {
                int tem = array[i];
                array[i] = array[index];
                array[index] = tem;
            }
        }
    }

    private void mapPao(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private int removeDuplicates(int[] arr) {//有序数组去重
        int [] array = {0,1,2,2,5,6,6,8};
        int i = 0;
        int j;
        for (j = 1; j < arr.length; j++) {
            if (arr[j] != arr[i]) {
                arr[++i] = arr[j];
            }
        }
        //j=1 -A1=1 i=1
        //j=2 -A2=2 i=2
        //j=3
        //j=4 -A3=5 i=3
        //j=5 -A4=6 i=4
        //j=6
        //j=7 -A5=8 i=5
        return i + 1;
    }
}
