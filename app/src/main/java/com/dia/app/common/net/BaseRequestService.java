package com.dia.app.common.net;



import java.lang.reflect.Type;

/**
 * @Author: Dia
 * @Date: 2018/5/13 18:26
 * @Description: 通用网络请求类
 **/
public class BaseRequestService<Request, Response> {

//  public Response post(String apiUrl, Request request, Type type) {
//    String appApiUrl = CompanyApplicationUtil.getAppApiUrl(apiUrl);
//    if (StringUtils.isEmpty(appApiUrl)) {
//      return null;
//    }
//
//    return OkHttpManager.getInstance().post(appApiUrl, JsonUtil.toJson(request), type);
//  }
//
//  public Response postDirect(String appApiUrl, Request request, Type type) {
//    return OkHttpManager.getInstance().post(appApiUrl, JsonUtil.toJson(request), type);
//  }
}
