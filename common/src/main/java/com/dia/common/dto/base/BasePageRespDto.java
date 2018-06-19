package com.dia.common.dto.base;

/**
 * @author: Dia
 * @Description: 分页响应实体
 * @version: v1.0.0
 * @date: 2018年6月14日20:19:56
 */
public class BasePageRespDto<T> extends BaseRespDto<T> {
  /**
   * 总条数
   */
  private int count;

  public BasePageRespDto() {
  }

  public BasePageRespDto(int code,String msg) {
	  super.setCode(code);
	  super.setMsg(msg);
  }
  
  public BasePageRespDto(int count, int code, String msg, T data) {
    super.setCode(code);
    super.setMsg(msg);
    super.setData(data);
    this.count = count;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
