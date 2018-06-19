package com.dia.app.common.util;

import com.dia.app.support.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TokenManager {
  @Autowired
  CacheManager cacheManager;

  private static final String CACHE_PREFIX = "token";
  private final static int TOKEN_EXPIRE = 12 * 60 * 60;

  public boolean exist(String token) {
    return cacheManager.exists(getCacheKey(token));
  }
  /**
   * 返回用户数据
   * @param token
   * @return User
   */
  public String get(String token) {
    return cacheManager.get(getCacheKey(token));
  }

  public void set(String token, String companyCode) {
    String cacheKey = getCacheKey(token);
    cacheManager.set(getCacheKey(token), companyCode);
    cacheManager.expire(cacheKey, TOKEN_EXPIRE);
  }

  public void delete(String token) {
    cacheManager.delete(getCacheKey(token));
  }

  private String getCacheKey(String token) {
    return CACHE_PREFIX + token;
  }
}
