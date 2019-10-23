package com.chaozhuo.parentmanager.mvvm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import com.chaozhuo.parentmanager.bean.OnLineConfigBean;


/**
 * Created by fewwind on 19-3-13.
 */

public class ConfigViewModel extends ViewModel {
    private MutableLiveData<OnLineConfigBean> mData;

    public MutableLiveData<OnLineConfigBean> getConfig() {
        if (mData == null) {
            mData = new MutableLiveData<>();
        }
        loadData();
        return mData;
    }

    private void loadData() {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);
                OnLineConfigBean lineConfigBean = new OnLineConfigBean();
                lineConfigBean.name = "liveData";
                mData.setValue(lineConfigBean);
                super.run();
            }
        }.start();
    }

}
