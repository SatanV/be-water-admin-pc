package com.dia.app.common.interceptor;


import com.dia.app.common.util.ResponseRender;
import com.dia.common.constants.ResponseCode;
import com.dia.common.util.BaseRespDtoWrapper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    ResponseRender.renderJson(response, new BaseRespDtoWrapper<>().error(ResponseCode.NOT_FOUND));
    return false;
  }
}
