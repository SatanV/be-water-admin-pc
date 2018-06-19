package com.dia.common.dto.base;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author: Dia
 * @Description: 响应实体类
 * @version: v1.0.0
 * @date: 2018年5月16日 下午2:02:01
 */
public class BaseRespDto<T> {
  @ApiModelProperty(notes = "响应编码")
  private int code;
  @ApiModelProperty(notes = "响应消息")
  private String msg;
  @ApiModelProperty(notes = "响应实体类")
  private T data;

  public BaseRespDto() {
  }

  public BaseRespDto(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

public int getCode() {
	return code;
}

public void setCode(int code) {
	this.code = code;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public T getData() {
	return data;
}

public void setData(T data) {
	this.data = data;
}

}
