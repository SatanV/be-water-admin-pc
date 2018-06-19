package com.dia.common.dto.base;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author: Dia
 * @Description: 公共请求参数
 * @version: v1.0.0
 * @date: 2018年6月14日20:18:58
 */
public class CommonReqDto {
  @ApiModelProperty(notes="当前请求时间戳")
  private long requestId;
  @ApiModelProperty(notes="接口版本号,当前版本传 1 即可")
  private int apiVer;
  @ApiModelProperty(notes="平台（1:pad,2:android,3:ios,4:pc）")
//  @Min(value=1,message="平台值不能小于1")
//  @Max(value=4,message="平台值不能大于4")
  private int platformId;
  @ApiModelProperty(notes="token")
//  @NotBlank(message="token值不能为空！")
  private String token;
  @ApiModelProperty(notes="机器码")
//  @NotBlank(message="机器码不能为空！")
  private String uuid;

  public long getRequestId() {
    return requestId;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }

  public int getApiVer() {
    return apiVer;
  }

  public void setApiVer(int apiVer) {
    this.apiVer = apiVer;
  }

  public int getPlatformId() {
    return platformId;
  }

  public void setPlatformId(int platformId) {
    this.platformId = platformId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

 
}