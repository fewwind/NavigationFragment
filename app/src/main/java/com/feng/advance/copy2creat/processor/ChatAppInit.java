package com.feng.advance.copy2creat.processor;

import com.feng.rounte_annotation.AppStart;
import com.feng.rounte_annotation.IAppInit;
import com.orhanobut.logger.Logger;

@AppStart(sort = 6)
public class ChatAppInit implements IAppInit {
    @Override public void init() {
        Logger.v("ChatInit");
    }
}
