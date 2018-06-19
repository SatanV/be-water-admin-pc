package com.dia.common.dto.base;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

/**
 * @author: Dia
 * @Description: 请求实体基类
 * @version: v1.0.0
 * @date: 2018年5月16日 下午2:02:01
 */
public class BaseReqDto<T> {

	@ApiModelProperty(notes = "公共参数", required = true)
	@Valid
	private CommonReqDto common;
	@ApiModelProperty(notes = "业务参数")
	@Valid
	private T data;

	public CommonReqDto getCommon() {
		return common;
	}

	public void setCommon(CommonReqDto common) {
		this.common = common;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
