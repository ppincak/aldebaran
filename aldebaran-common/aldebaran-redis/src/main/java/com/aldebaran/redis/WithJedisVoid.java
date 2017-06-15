package com.aldebaran.redis;

import redis.clients.jedis.Jedis;


public interface WithJedisVoid {

    void withJedis(Jedis jedis);
}
