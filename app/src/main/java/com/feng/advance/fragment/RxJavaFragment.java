package com.feng.advance.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.View;
import com.feng.advance.R;
import com.feng.advance.base.BaseFragment;
import com.feng.advance.copy2creat.myrxjava.FObservable;
import com.feng.advance.copy2creat.myrxjava.FObservableOnSubscribe;
import com.feng.advance.copy2creat.myrxjava.FObserver;
import com.feng.advance.copy2creat.myrxjava.IFilter;
import com.orhanobut.logger.Logger;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
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
        return R.layout.fragment_main;
    }


    @Override
    protected void initViewsAndEvents(View view) {
        test();
    }


    Observable<Integer> one;
    Observable<String> two;


    private void test() {
        one = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            SystemClock.sleep(2000);
            emitter.onNext(2000);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
        two = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            SystemClock.sleep(3000);
            emitter.onNext("3000");
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
        Logger.v("Start= ");
        //merge();
        //zip();
        concat();
    }


    private void merge() {
        //调度器在one和two时，按照时间顺序发射，2s和3s后输出，一共3s，即使two在前，依然是one先输出
        //调度器在merge时，2s之后在等3s，一共5s
        Observable.merge(two,one ).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override public void accept(Object o) throws Exception {
                Logger.i("merge = " + o);
            }
        });
    }


    private void concat() {
        //按照observable源的顺序发射，调度器在one和two时，2s后等3s输出，一共5s，如果two在前，先等3s再等2s共5s
        //调度器在concat时,跟merge类似，2s之后在等3s，一共5s
        Observable.concat(one, two).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override public void accept(Object o) throws Exception {
                Logger.i("merge = " + o);
            }
        });
    }


    private void zip() {
        //调度器在zip时，5s后返回结果
        //调度器在one和two时，3s后输出，一共3s，他们是个整体
        Observable.zip(one, two, new BiFunction<Object, Object, Object>() {
            @Override public Object apply(Object o, Object o2) throws Exception {
                Logger.v("One = " + o + " two = " + o2);
                return "3000";
            }
        }).subscribeOn(Schedulers.io()).subscribe(o -> {
            Logger.w("zip = " + o);
        });
    }


    private void flow() {
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
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        Logger.i("Map 3");
                        return "3";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Logger.e("result = " + s);
                    }
                });
    }


    private void testCustomRx() {
        //整体上看是双向回调，observable接受一个接口最为成员，发生订阅的时候此接口作为桥梁，调用接口方法observer作为参数传递，然后observer在执行自己的方法，这是第二层回调
        //每个操作符都创建Observable，同时Observable 的subscribe方法接收observer对象，接收下游的observer对象后创建新的Observer，并把下游传过来的obs作为成员变量
        FObservable.creat(new FObservableOnSubscribe<String>() {
            @Override
            public void subscribe(FObserver<String> observer) {
                Logger.w("Creat subscribe " + observer);
                observer.onNext("One");
            }
        }).observeOn(Schedulers.io()).filter(new IFilter<String>() {
            @Override
            public boolean test(String s) {
                return true;
            }
        }).subscribe(new FObserver<String>() {
            @Override
            public void onNext(String s) {
                Logger.e("ONNext " + s);
            }


            @Override
            public void onError() {

            }


            @Override
            public void onComplete() {

            }
        });

        Observable.just("").doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        });
    }

}
