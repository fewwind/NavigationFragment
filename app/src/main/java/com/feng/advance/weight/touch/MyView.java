package com.feng.advance.weight.touch;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fewwind on 19-1-28.
 */

public class MyView {

    protected List<MyView> myViews = new ArrayList<>();

    public boolean dispatchTouch() {
        Logger.v(getClass().getSimpleName()+" == dispatch");
        return onTouch();
    }

    public boolean onTouch() {
        Logger.e(getClass().getSimpleName()+" == onTouch");
        return false;

    }

    public void addView(MyView view) {
        myViews.add(view);
    }
}
