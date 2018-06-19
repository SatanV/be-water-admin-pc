package com.dia.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

/**
 * @author: Dia
 * @Description: 代码工具类
 * @version: v1.0.0
 * @date: 2018年5月18日 下午3:41:26
 */
public class JsonUtil {
  public static Gson gson;
  public static JsonParser parser = new JsonParser();

  static {
	  gson = new GsonBuilder()  
  			.setDateFormat("yyyy-MM-dd HH:mm:ss")  
  			.create();
  }
  public static String toJson(Object obj) {
    return gson.toJson(obj);
  }

  public static String fromJson(String json, Type jsonType) {
    return gson.fromJson(json, jsonType);
  }

  public static <T> T fromJson(String json, Class clz) {
    return (T) gson.fromJson(json, clz);
  }
}
