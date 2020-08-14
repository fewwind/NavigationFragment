package com.feng.advance.copy2creat.apt.com.fewwind.learn;

import com.feng.advance.MainActivity;
import java.lang.Class;
import java.lang.String;
import java.util.HashMap;

public class appAutoCreateModuleActivityMap_app {
  public static void initActivityMap(HashMap<String, Class> activityMap) {
    activityMap.put("main", MainActivity.class);
    activityMap.put("2", MainActivity.class);
  }
}
