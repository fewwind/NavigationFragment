package com.chaozhuo.parentmanager.weight.touch;

import com.orhanobut.logger.Logger;

/**
 * Created by fewwind on 19-1-28.
 */

public class MyViewGroup extends MyView {

    public boolean dispatchTouch() {
        Logger.i(getClass().getSimpleName() + " Group== dispatch");
        for (int i = 0; i < myViews.size(); i++) {
            MyView view = myViews.get(i);
            boolean touch = view.dispatchTouch();
            if (!touch) onTouch();
        }
        return false;
    }

}
