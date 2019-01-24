package com.chaozhuo.parentmanager.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;

import com.chaozhuo.parentmanager.MainActivity;
import com.chaozhuo.parentmanager.R;

public class Sub0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addCategory("1");
        PendingIntent intent0 = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Intent intentA = new Intent(this, MainActivity.class);
        intentA.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentA.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intentA.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        PendingIntent intent2 = PendingIntent.getActivity(this, 0, intentA, PendingIntent.FLAG_ONE_SHOT);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + DateUtils.SECOND_IN_MILLIS * 3, intent0);
        Intent intent1 = new Intent(this, SplashActivity.class);
        intent1.addCategory("1");
        PendingIntent intent00 = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_ONE_SHOT);
        manager.cancel(intent00);
//        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000 * 12, intent2);
    }

    public static void start(Context context) {
//        try {
//            context.startActivity(new Intent(context,context.getClassLoader().loadClass("com.chaozhuo.ad86.MainActivity")));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        context.startActivity(new Intent(context, Sub0Activity.class));
    }
}
