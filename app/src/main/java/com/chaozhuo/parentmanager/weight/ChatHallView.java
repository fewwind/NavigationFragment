package com.chaozhuo.parentmanager.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chaozhuo.parentmanager.R;
import com.orhanobut.logger.Logger;

public class ChatHallView extends FrameLayout {
    public ChatHallView(Context context) {
        this(context, null);
    }

    public ChatHallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_chat_hall_default, this);
        View view = findViewById(R.id.circle_bg);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Logger.w(":la = " + layoutParams);
    }

}
