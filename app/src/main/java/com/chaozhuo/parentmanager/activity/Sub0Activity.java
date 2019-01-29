package com.chaozhuo.parentmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.chaozhuo.parentmanager.R;
import com.orhanobut.logger.Logger;

public class Sub0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_touch_event);
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
