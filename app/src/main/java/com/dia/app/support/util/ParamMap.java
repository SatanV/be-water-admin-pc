package com.dia.app.support.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wuhua.wwh
 * @Date: 2018/5/7 19:04
 * @Description:
 **/
public class ParamMap {

  public static ThreadLocal<ParamMap> current = new ThreadLocal<ParamMap>();

  public static ParamMap current() {
    return current.get();
  }

  public static void currentSet(ParamMap pm) {
    current.set(pm);
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getAction() {
    return this.action;
  }

  private String action;

  private Map<String, Object> map;

  public ParamMap(Map<String, ?> map) {
    if (map == null) {
      this.map = new HashMap<String, Object>();
      return;
    }
    this.map = new HashMap<String, Object>(map);
  }

  public String getStringReturnEmpty(String key) {
    if (map == null)
      return null;
    String result = (String)this.map.get(key);

    return (result != null)?result:"";
  }

  public String getString(String key) {
    if (map == null)
      return null;
    Object value = this.map.get(key);
    if (value == null)
      return null;
    return String.valueOf(value);
  }

  public String getString(String key,String defaultValue) {
    if (map == null)
      return null;
    Object value = this.map.get(key);
    if (value == null)
      return defaultValue;
    return String.valueOf(value);
  }

  public Long getLong(String key) {
    return getLong(key, null);
  }

  public Long getLong(String key, Long defaultValue) {
    if (map == null)
      return defaultValue;
    Object obj = map.get(key);
    if (obj == null)
      return defaultValue;
    try {
      Long value = Long.parseLong(obj.toString());
      return value;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public Integer getInteger(String key) {
    return getInteger(key, null);
  }

  public Integer getInteger(String key, Integer defaultValue) {
    if (map == null)
      return defaultValue;
    Object obj = map.get(key);
    if (obj == null)
      return defaultValue;
    try {
      Integer value = Integer.valueOf(obj.toString());
      return value;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public Double getDouble(String key, Double defaultValue) {
    if (map == null)
      return defaultValue;
    Object obj = map.get(key);
    if (obj == null)
      return defaultValue;
    try {
      Double value = Double.parseDouble(obj.toString());
      return value;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public Float getFloat(String key, Float defaultValue) {
    if (map == null)
      return defaultValue;
    Object obj = map.get(key);
    if (obj == null)
      return defaultValue;
    try {
      Float value = Float.parseFloat(obj.toString());
      return value;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public Object get(String key) {
    if (map == null)
      return null;
    return map.get(key);
  }

  public Boolean getBoolean(String key) {
    return getBoolean(key, null);
  }

  public Boolean getBoolean(String key, Boolean defaultValue) {
    if (map == null)
      return defaultValue;
    Object obj = map.get(key);
    if (obj == null)
      return defaultValue;
    try {
      Boolean value = Boolean.valueOf(obj.toString());
      return value;
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  @SuppressWarnings("unchecked")
public ParamMap getParamMap(String key) {
    Map<String, ?> map2 = null;
    try {
      map2 = (Map<String, ?>)map.get(key);
    } catch (Exception e) {
    }
    if (map2 == null) {
      return null;
    }
    return new ParamMap(map2);
  }

  public void addAll(Map<String, String> map) {
    if (map == null)
      return;
    for (String key : map.keySet()) {
      this.map.put(key, map.get(key));
    }
  }

  public void put(String key, Object value) {
    if (this.map != null)
      this.map.put(key, value);
  }

  public Map<String, Object> toMap() {
    return map;
  }

  @Override
  public String toString() {
    return (map != null)?map.toString():null;
  }
}
