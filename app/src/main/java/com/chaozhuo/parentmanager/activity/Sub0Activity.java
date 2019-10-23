package com.chaozhuo.parentmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.chaozhuo.parentmanager.R;
import com.chaozhuo.parentmanager.mvvm.HandlerObserver;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Sub0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_touch_event);
        // Lifecycle 感知生命周期。ViewModle-Activity之间共享数据，livedata，通过观察者模式通知数据变化（用到了lifecycle）
        getLifecycle().addObserver(new HandlerObserver(new Handler()));
        //程序计数器 堆 栈 本地方法 方法区（常量池，静态变量，class文件）
        //为什检测内存泄露需要两次，1是gc不一定马上执行，2是给被回收的activity需要添加到列表时间
        Set<String> set = new HashSet<>();
        // Map的hashcode是用来决定放在哪个桶，也就是数组的位置。eques是链表中key是否相等，决定新建还是覆盖
        List list = new ArrayList();
        LinkedList listLink = new LinkedList();
        list.contains("");
        listLink.contains("");
        //ViewGroup 传给子View的是measureSpec。里边包含size和mode，如果是unspecial则是view自己的测量宽高（），
    }

    public static void start(Context context) {
//        try {
//            context.startActivity(new Intent(context,context.getClassLoader().loadClass("com.chaozhuo.ad86.MainActivity")));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        context.startActivity(new Intent(context, Sub0Activity.class));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.e("Avtivity Touch "+event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.d("Avtivity dispatchTouchEvent "+ev);
        return super.dispatchTouchEvent(ev);
    }
}
