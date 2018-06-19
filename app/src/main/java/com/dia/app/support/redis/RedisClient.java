package com.dia.app.support.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Component
public class RedisClient {
  private final ShardedJedisPool pool;

  @Autowired
  public RedisClient(ShardedJedisPool pool) {
    this.pool = pool;
  }

  public String get(String key) {
    try (ShardedJedis shardedJedis = pool.getResource()) {
      return shardedJedis.get(key);
    }
  }

  public String set(String key, String value) {
    try (ShardedJedis shardedJedis = pool.getResource()) {
      return shardedJedis.set(key, value);
    }
  }

  public String set(String key, int seconds, String value) {
    try (ShardedJedis shardedJedis = pool.getResource()) {
      return shardedJedis.setex(key, seconds, value);
    }
  }

  public boolean exists(String key) {
    try (ShardedJedis shardedJedis = pool.getResource()) {
      return shardedJedis.exists(key);
    }
  }

  public long expire(String key, int seconds) {
    try (ShardedJedis shardedJedis = pool.getResource()) {
      return shardedJedis.expire(key, seconds);
    }
  }

  public void del(String key) {
    ShardedJedis jedis = null;
    try {
      jedis = pool.getResource();
      jedis.del(key);
    } finally {
      if (jedis != null) {
        jedis.close();
      }
    }
  }
}
