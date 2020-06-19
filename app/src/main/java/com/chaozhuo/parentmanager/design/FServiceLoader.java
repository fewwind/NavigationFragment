package com.chaozhuo.parentmanager.design;

import com.chaozhuo.parentmanager.copy2creat.TopUtil;
import com.chaozhuo.parentmanager.copy2creat.okhttp.Intercept;
import java.util.ServiceLoader;

public class FServiceLoader {
    public static void load(){
        ServiceLoader<Intercept> loader = ServiceLoader.load(Intercept.class);
        while (loader.iterator().hasNext()){
            Intercept service = loader.iterator().next();
            TopUtil.print(service);
        }
    }
}
