package com.aldebaran.redis;

import redis.clients.jedis.Jedis;


public interface WithJedis<T> {

    T withJedis(Jedis jedis);
}
