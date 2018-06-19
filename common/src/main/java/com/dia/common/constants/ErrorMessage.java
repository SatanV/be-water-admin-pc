package com.dia.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Dia
 * @Description: 错误信息
 * @version: v1.0.0
 * @date: 2018年6月14日 下午 20:06:28
 */
public class ErrorMessage {

  public static final Map<Integer,String> map = new HashMap<Integer, String>();

  static {
    map.put(ResponseCode.OK, "OK");
    map.put(ResponseCode.NOT_FOUND, "接口不存在");
    map.put(ResponseCode.INTERNAL_ERROR, "服务器内部错误");
    map.put(ResponseCode.INTERNAL_DB_ERROR, "服务器内部错误，与数据库有关的错误");
    
  }

}
