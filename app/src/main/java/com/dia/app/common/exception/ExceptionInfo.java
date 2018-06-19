package com.dia.app.common.exception;

import java.io.Serializable;

/**
 * @author: Dia
 * @Description: 异常处理
 * @version: v1.0.0
 * @date: 2018年6月14日 下午20:01:11
 */
public class ExceptionInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
    private String code;
    private String msg;
    private String createTime;
    private String updateTime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
