package com.chaozhuo.parentmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.base.BaseFragment;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaFragment extends BaseFragment {

    public RxJavaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        RxJavaFragment fragment = new RxJavaFragment();
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
        test();
    }

    private void test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                Logger.v("onNext 1");
                Thread.sleep(1000);
                emitter.onNext("2");
                Logger.d("onNext 2");
//                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                Logger.i("Map 3");
                return "3";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.e("result = " + s);
            }
        });
    }


}
