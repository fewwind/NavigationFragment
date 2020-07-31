package com.feng.advance.weight;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.feng.advance.R;

/**
 * Created by fewwind on 18-12-7.
 */

public class HorHeadView extends FrameLayout {

    public HorHeadView(@NonNull Context context) {
        this(context, null);
    }

    public HorHeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_header_hor, this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }

    private void setForColor() {
        SpannableString spannableString = new SpannableString("设置文字的前景色为淡蓝色");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableString.setSpan(colorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}
