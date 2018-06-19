package com.dia.common.constants;

/**
 * @author: Dia
 * @Description: 错误编码
 * @version: v1.0.0
 * @date: 2018年5月25日 上午9:42:22
 */
public class ResponseCode {
  public static final int OK = 200;// 成功
  public static final int NOT_FOUND = 404;// 找不到接口

  // 服务器内部相关
  public static final int INTERNAL_ERROR = 101; // 服务器内部错误
  public static final int INTERNAL_DB_ERROR = 102; // 服务器内部错误，与数据库有关的错误

  // 参数相关错误
  public static final int PARAMS_ERROR = 203;//参数错误


  
  
}
