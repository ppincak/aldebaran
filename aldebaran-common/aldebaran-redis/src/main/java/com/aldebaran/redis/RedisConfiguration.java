package com.aldebaran.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@Conditional(RedisEnabledCondition.class)
public class RedisConfiguration {

    @Bean
    public JedisPoolConfig jedisPoolConfig(RedisProperties redisProperties) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getPoolSize());
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(RedisProperties redisProperties,
                               JedisPoolConfig jedisPoolConfig) {
        return  new JedisPool(jedisPoolConfig,
                              redisProperties.getRedisHost(),
                              redisProperties.getRedisPort());
    }
}