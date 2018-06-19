package com.dia.common.util;


import com.dia.common.constants.ErrorMessage;
import com.dia.common.constants.ResponseCode;
import com.dia.common.dto.base.BasePageRespDto;
import com.dia.common.dto.base.BaseRespDto;

/**
 * @author: Dia
 * @Description: 响应映射实体
 * @version: v1.0.0
 * @date: 2018年5月16日 下午2:02:01
 */
public class BaseRespDtoWrapper<T> {

	public BaseRespDto<T> success() {
		return new BaseRespDto<T>(ResponseCode.OK, ErrorMessage.map.get(ResponseCode.OK), null);
	}

	public BaseRespDto<T> success(T data) {
		return new BaseRespDto<T>(ResponseCode.OK, ErrorMessage.map.get(ResponseCode.OK), data);
	}
	
	public BaseRespDto<T> success(T data,int code) {
		return new BaseRespDto<T>(ResponseCode.OK, ErrorMessage.map.get(code), data);
	}
	
	public BaseRespDto<T> internalError(String errorMsg) {
		return new BaseRespDto<T>(ResponseCode.INTERNAL_ERROR, errorMsg, null);
	}

	public BasePageRespDto<T> success(int count, T data) {
		return new BasePageRespDto<>(count, ResponseCode.OK, ErrorMessage.map.get(ResponseCode.OK), data);
	}

	public BaseRespDto<T> error(int code) {
		String msg = ErrorMessage.map.get(code);
		return new BaseRespDto<T>(code, msg, null);
	}

	public BaseRespDto<T> error(int code, String msg) {
		// String msg = ErrorMessage.map.get(code);
		return new BaseRespDto<T>(code, msg, null);
	}

	public BasePageRespDto<T> internalPageError(String errorMsg) {
		return new BasePageRespDto<T>(ResponseCode.INTERNAL_ERROR, errorMsg);
	}

	public BaseRespDto<T> loginError(String errorMsg) {
		return new BaseRespDto<T>(ResponseCode.PARAMS_ERROR, errorMsg, null);
	}

	public BasePageRespDto<T> pageSuccess(T data, int count) {
		return new BasePageRespDto<T>(count, ResponseCode.OK, ErrorMessage.map.get(ResponseCode.OK), data);
	}
	
	public BasePageRespDto<T> pageError(int code) {
		String msg = ErrorMessage.map.get(code);
		return new BasePageRespDto<T>(0,code, msg, null);
	}

	public BasePageRespDto<T> pageError(int code, String msg) {
		return new BasePageRespDto<T>(0,code, msg, null);
	}

	public BaseRespDto<T> paramsError(String errorMsg){
		return new BaseRespDto<T>(ResponseCode.PARAMS_ERROR, errorMsg, null);
	}

	public BaseRespDto<T> resp(int code) {
		if (ResponseCode.OK == code) {
			return success();
		}
		return error(code);
	}


	public BaseRespDto<T> resp(int code, String msg, T data) {
		if (ResponseCode.OK == code) {
			return success(data);
		}
		return error(code, msg);
	}


	public BasePageRespDto<T> pageResp(int count, int code, String msg, T data) {
		if (ResponseCode.OK == code) {
			return success(count, data);
		}
		return pageError(code, msg);
	}

}
