package com.dia.app.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(CORSInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.info("CORSInterceptor");

    // 设置可跨域访问
    if(request.getHeader(HttpHeaders.ORIGIN) != null){
      response.setContentType("application/json; charset=utf-8");
      response.addHeader("Access-Control-Allow-Origin","*"); // 测试环境
      response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
      response.addHeader("Access-Control-Allow-Credentials", "true");
      response.addHeader("Access-Control-Max-Age", "1000");
      response.addHeader("Access-Control-Allow-Headers","Content-Type");
    }
    return true;
  }
}
