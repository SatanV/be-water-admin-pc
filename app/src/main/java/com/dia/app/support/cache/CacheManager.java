package com.dia.app.support.cache;

import com.dia.app.support.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CacheManager {

  @Autowired
  RedisClient redisClient;

  public String get(String key) {
    return redisClient.get(key);
  }

  public String set(String key, String value) {
    return redisClient.set(key, value);
  }

  public String set(String key, int seconds, String value) {
    return redisClient.set(key, seconds, value);
  }

  public boolean exists(String key) {
    return redisClient.exists(key);
  }

  public long expire(String key, int seconds) {
    return redisClient.expire(key, seconds);
  }

  public void delete(String key) {
    redisClient.del(key);
  }

}
