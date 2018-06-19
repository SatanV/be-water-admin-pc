package com.dia.app.support.stat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.*;

/**
 * @Author: wuhua.wwh
 * @Date: 2018/4/27 20:12
 * @Description:
 **/
public class StatLogBuilder {
  private static final Logger logger = LoggerFactory.getLogger(StatLogBuilder.class);

  private Map<String, String> dataMap = new HashMap<>();

  private boolean isReserve(String key){
    return StatLogConfig.KEYWORD_LIST.contains(key);
  }

  public StatLogBuilder module(String module) {
    dataMap.put(StatLogConfig.KEY_ACTION, module);
    return this;
  }

  public StatLogBuilder action(String action) {
    dataMap.put(StatLogConfig.KEY_ACTION, action);
    return this;
  }

  public StatLogBuilder add(String key, String value) {
    if (!isReserve(key)){
      dataMap.put(key, value);
    }
    return this;
  }

  public StatLogBuilder add(Map<String, String> extraMap) {
    dataMap.putAll(extraMap);
    return this;
  }

  @Async("commonExecutor") //配置类中的方法名
  public void commit(){
    StringBuilder sb = new StringBuilder();
    Iterator<Map.Entry<String, String>> iter = dataMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<String, String> entry = iter.next();
      String key = entry.getKey();
      String value = entry.getValue();
      sb.append(key).append("=").append(value).append(StatLogConfig.KEY_SEPARATOR);
    }

    String result = sb.toString();
    if (StringUtils.isNotBlank(result)) {
      result = result.substring(0, result.length() - 1);
    }

    if (StringUtils.isBlank(result)){
      return;
    }
    logger.info("thread id = ", Thread.currentThread().getId());
    logger.info(result);
  }

  @Async
  public String asyncMethodWithVoidReturnType() {
    System.out.println("线程名称："+Thread.currentThread().getName() + " be ready to read data!");
    try {
      Thread.sleep(1000 * 5);
      System.out.println("---------------------》》》无返回值延迟3秒：");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "已进入到异步";
  }
}
