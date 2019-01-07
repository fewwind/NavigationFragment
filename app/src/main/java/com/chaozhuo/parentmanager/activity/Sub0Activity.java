package com.chaozhuo.parentmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chaozhuo.parentmanager.R;

public class Sub0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub0);
    }

    public static void start(Context context){
        try {
            context.startActivity(new Intent(context,context.getClassLoader().loadClass("com.chaozhuo.ad86.MainActivity")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        context.startActivity(new Intent(context,Sub0Activity.class));
    }
}
