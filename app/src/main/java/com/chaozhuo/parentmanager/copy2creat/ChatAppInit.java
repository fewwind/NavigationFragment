package com.chaozhuo.parentmanager.copy2creat;

import com.chaozhuo.rounte_annotation.AppStart;
import com.chaozhuo.rounte_annotation.IAppInit;
import com.orhanobut.logger.Logger;

@AppStart(sort = 6)
public class ChatAppInit implements IAppInit {
    @Override public void init() {
        Logger.v("ChatInit");
    }
}
