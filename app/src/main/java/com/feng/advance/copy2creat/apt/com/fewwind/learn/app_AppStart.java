package com.feng.advance.copy2creat.apt.com.fewwind.learn;

import com.feng.rounte_annotation.AppInitSort;
import java.util.ArrayList;

public class app_AppStart {
  public static void initModule(ArrayList<AppInitSort> appStartList) {
    appStartList.add(new AppInitSort(6, "com.feng.advance.copy2creat.processor.ChatAppInit"));
  }
}
