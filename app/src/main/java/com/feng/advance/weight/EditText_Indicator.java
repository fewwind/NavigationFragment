package com.feng.advance.weight;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import com.feng.advance.util.StringUtil;

public class EditText_Indicator extends android.support.v7.widget.AppCompatTextView {

    private int max;

    public EditText_Indicator(Context context) {
        this(context, null);
    }

    public EditText_Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEditText(EditText edit, int max) {
        this.max = max;
        setVisibility(GONE);
        setIndicator(StringUtil.stringLegth(edit.getText().toString()));
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setIndicator(StringUtil.stringLegth(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && StringUtil.stringLegth(s.toString()) > max) {
                    int index = StringUtil.stringMaxIndex(s.toString(), max);
                    if (index > 0) {
                        s.delete(index + 1, s.toString().length());
                    }
                }
            }
        });
        edit.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                setVisibility(hasFocus ? VISIBLE : GONE);
            }
        });
    }


    private void setIndicator(float index) {
        setEnabled(index < max);
        setText((int) (max - index) + "");
    }
}
