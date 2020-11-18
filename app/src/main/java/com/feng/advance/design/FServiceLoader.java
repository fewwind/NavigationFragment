package com.feng.advance.design;

import com.feng.advance.copy2creat.TopUtil;
import com.feng.rounte_annotation.IAppInit;
import java.util.Iterator;
import java.util.ServiceLoader;

public class FServiceLoader {
    public static void load(){
        ServiceLoader<IAppInit> loader = ServiceLoader.load(IAppInit.class);
        Iterator<IAppInit> iterator = loader.iterator();
        while (iterator.hasNext()){
            IAppInit service = iterator.next();
            TopUtil.print(service);
        }
    }
}
