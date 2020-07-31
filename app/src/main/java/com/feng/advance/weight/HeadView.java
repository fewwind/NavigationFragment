package com.feng.advance.weight;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.feng.advance.R;

/**
 * Created by fewwind on 18-12-7.
 */

public class HeadView extends FrameLayout {

    TextView mUseTime;

    public HeadView(@NonNull Context context) {
        this(context, null);
    }

    public HeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_header, this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        mUseTime = findViewById(R.id.tv_use_time);
        setUseTime(16, 16);
    }

    public void setUseTime(int hour, int min) {
        mUseTime.setText(getFormat(hour + "", min + ""));
    }

    private SpannableString getFormat(String hour, String min) {
        String info = String.format(getContext().getString(R.string.statis_use_time_content), hour, min);
        SpannableString span = new SpannableString(info);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(4f);
        span.setSpan(sizeSpan, info.indexOf(hour.charAt(0)), info.indexOf(hour.charAt(0)) + hour.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        RelativeSizeSpan sizeSpan2 = new RelativeSizeSpan(4f);
        int indexOfMin = info.indexOf(min.charAt(0), hour.length() + 1);
        span.setSpan(sizeSpan2, indexOfMin, indexOfMin + min.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return span;
    }

    private void setForColor() {
        SpannableString spannableString = new SpannableString("设置文字的前景色为淡蓝色");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableString.setSpan(colorSpan, 9, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}
