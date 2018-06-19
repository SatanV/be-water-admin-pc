package com.dia.app.common.interceptor;

import com.dia.app.common.filter.CustomHttpRequestWrapper;
import com.dia.app.common.util.*;
import com.dia.common.constants.ResponseCode;
import com.dia.common.util.BaseRespDtoWrapper;
import com.dia.common.util.JsonUtil;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class TokenInterceptor extends HandlerInterceptorAdapter {
  private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

  private final TokenManager tokenManager;


  public TokenInterceptor( TokenManager tokenManager ) {
    this.tokenManager = tokenManager;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.info("TokenInterceptor");
    CustomHttpRequestWrapper requestWrapper = null;
    if (request instanceof CustomHttpRequestWrapper) {
      requestWrapper = (CustomHttpRequestWrapper) request;
    } else {
      requestWrapper = new CustomHttpRequestWrapper(request);
    }

    String method = request.getMethod();
    if ("OPTIONS".equalsIgnoreCase(method)) {
      return true;
    }

    JsonObject requestBodyJson = getRequestBodyJson(requestWrapper);
    if (requestBodyJson == null) {
      ResponseRender.renderJson(response, new BaseRespDtoWrapper<>().error(ResponseCode.PARAMS_ERROR));
      return false;
    }

    JsonObject jCommon = requestBodyJson.get("common").getAsJsonObject();
    String token = jCommon.get("token").getAsString();
    int platformId = jCommon.get("platformId").getAsInt();
    logger.info(token);

//    if (StringUtils.isEmpty(token)) {
//      ResponseRender.renderJson(response, new BaseRespDtoWrapper<>().error(ResponseCode.TOKEN_PARAMETER_NOT_EXIST));
//      return false;
//    }
//    if (PlatformConstants.ADMIN == platformId) {
//      Admin admin = adminTokenManager.get(token);
//      if (null == admin) {
//        ResponseRender.renderJson(response, new BaseRespDtoWrapper<>().error(ResponseCode.TOKEN_NOT_MATCH));
//        return false;
//      }
//      return true;
//    } else if (PlatformConstants.ENTERPRISE == platformId) {
//      Enterprise enterprise = enterpriseTokenManager.get(token);
//      if (null == enterprise) {
//        ResponseRender.renderJson(response, new BaseRespDtoWrapper<>().error(ResponseCode.TOKEN_NOT_MATCH));
//        return false;
//      }
//      return true;
//    }
//
//    String companyCode = tokenManager.get(token);
//    if (StringUtils.isEmpty(companyCode)) {
//      ResponseRender.renderJson(response, new BaseRespDtoWrapper<>().error(ResponseCode.TOKEN_NOT_MATCH));
//      return false;
//    }
//
//    CompanyApp companyApplication = companyCacheManager.getCompanyApplication(companyCode);
//    if (companyApplication == null) {
//      ResponseRender.renderJson(response, new BaseRespDtoWrapper<>().error(ResponseCode.TOKEN_NOT_MATCH));
//      return false;
//    }
//
//    HashMap<String, CompanyApp> hashMap = new HashMap<String, CompanyApp>();
//    hashMap.put("companyApp", companyApplication);
//    ParamMap.currentSet(new ParamMap(hashMap));

    return true;
  }

  private JsonObject getRequestBodyJson(CustomHttpRequestWrapper request) {
    String method = request.getMethod();
    JsonObject jRequestBody = null;
    if ("POST".equals(method)) {// post请求获取参数方式
      BufferedReader br;
      StringBuilder bodyStr = new StringBuilder();
      try {
        br = request.getReader();
        String str;
        while ((str = br.readLine()) != null) {
          bodyStr.append(str);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      String query = bodyStr.toString();
      try {
        jRequestBody = JsonUtil.parser.parse(query).getAsJsonObject();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return jRequestBody;
  }

}
