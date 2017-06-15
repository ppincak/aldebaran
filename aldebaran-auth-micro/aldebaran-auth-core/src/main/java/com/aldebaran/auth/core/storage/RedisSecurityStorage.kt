package com.aldebaran.auth.core.storage

import com.aldebaran.redis.WithJedis
import org.springframework.beans.factory.annotation.Autowired
import redis.clients.jedis.JedisPool


const val jtiKey = "\$jti";

open class RedisSecurityStorage
        @Autowired constructor(val jedisPool: JedisPool) : SecurityStorage {

    override fun revoke(jti: String) {
        execute(WithJedis {
            jedis -> jedis.sadd(jtiKey, jti) }
        )
    }

    override fun isRevoked(jti: String): Boolean {
        return execute(WithJedis<Boolean> {
            jedis -> jedis.sismember(jtiKey, jti) }
        );
    }

    private fun<T> execute(withJedis: WithJedis<T>) : T {
        val jedis = jedisPool.resource
        val result = withJedis.withJedis(jedis)
        jedis.close()
        return result
    }
}