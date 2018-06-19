package com.dia.app.support.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisConfig {
  @Bean
  public JedisShardInfo jedisShardInfo(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") Integer port, @Value("${spring.redis.password}") String password) {
    JedisShardInfo info = new JedisShardInfo(host, port);
    info.setPassword(password);
    return info;
  }

  @Bean
  public JedisPoolConfig jedisPoolConfig() {
    return getJedisPoolConfig();
  }

  private static JedisPoolConfig getJedisPoolConfig() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(1024);
    config.setMaxIdle(8);
    config.setMaxWaitMillis(15000);
    config.setTestWhileIdle(true);
    config.setBlockWhenExhausted(true);
    return config;
  }

  @Bean
  public ShardedJedisPool shardedJedisPool(@Qualifier("jedisPoolConfig") JedisPoolConfig config, @Qualifier("jedisShardInfo") JedisShardInfo info) {
    List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();
    infoList.add(info);
    return new ShardedJedisPool(config, infoList);
  }

  @Bean
  public JedisConnectionFactory jedisConnectionFactory(@Qualifier("jedisShardInfo") JedisShardInfo info) {
    return new JedisConnectionFactory(info);
  }

  @Bean
  public RedisTemplate redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory factory) {
    RedisTemplate redisTemplate = new RedisTemplate();
    redisTemplate.setConnectionFactory(factory);
    return redisTemplate;
  }
}
