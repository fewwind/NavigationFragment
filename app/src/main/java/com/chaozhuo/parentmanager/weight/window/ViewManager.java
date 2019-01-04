package com.chaozhuo.parentmanager.weight.window;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;

import com.chaozhuo.common.BaseApp;

import java.util.ArrayList;
import java.util.List;


public class ViewManager {
    private static final String TAG = "ViewManager";
    private static ViewManager sViewManager;
    private FloatView mFloatView = null;

    private WindowManager mWindowManager = null;
    private Context mContext;


    private List<String> mUpdateModePkg = new ArrayList<>();
    private boolean isShowMode;

    ViewManager() {
        mContext = BaseApp.app;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public static ViewManager get() {
        if (sViewManager == null) {
            sViewManager = new ViewManager();
        }

        return sViewManager;
    }

    // 左侧浮动view
    public void addAllView() {
        if (mFloatView == null){
            mFloatView = new FloatView(mContext);
            mWindowManager.addView(mFloatView, mFloatView.getLayoutParams());
        }
    }

    public void removeAllView() {
        if (mFloatView != null) {
            mFloatView.removeCallbacks(hideFloatLogo);
            mWindowManager.removeView(mFloatView);
            mFloatView = null;
        }
    }


    public void exitShowMode() {

        if (mFloatView != null) {
            mFloatView.setVisibility(View.INVISIBLE);
        }

    }


    private Runnable hideFloatLogo = new Runnable() {
        @Override
        public void run() {
            if (mFloatView != null && mFloatView.getParent() != null) {
                mFloatView.setVisibility(View.INVISIBLE);
            }
        }
    };


    public Rect getFloatViewPosition() {
        if (mFloatView != null) {
            return new Rect(0, 0,
                    mFloatView.getFloatViewX() + mFloatView.getWidth() / 2,
                    mFloatView.getFloatViewY() + mFloatView.getHeight() / 2);
        }
        return null;
    }

    public void setFloatViewOffsetX(float offsetX) {
        if (mFloatView != null) {
            mFloatView.updatePositionX(offsetX);
        }
    }


}
