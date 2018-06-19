package com.dia.app.support.stat;

import java.util.HashMap;

/**
 * @Author: wuhua.wwh
 * @Date: 2018/4/27 20:34
 * @Description:
 **/
public class StatLog {

  public static StatLogBuilder newBuilder(){
    return new StatLogBuilder();
  }

  public static void businessLog(String module, String action, HashMap<String, String> extraMap){
    StatLog.newBuilder().module(module).action(action).add(extraMap).commit();
  }
}
