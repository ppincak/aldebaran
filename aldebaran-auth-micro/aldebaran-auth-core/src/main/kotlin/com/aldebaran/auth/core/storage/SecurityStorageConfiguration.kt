package com.aldebaran.auth.core.storage

import com.aldebaran.redis.RedisCondition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import redis.clients.jedis.JedisPool


@Configuration
open class SecurityStorageConfiguration {

    @Bean
    @Primary
    @Conditional(RedisCondition.Disabled::class)
    open fun localSecurityStorage() : SecurityStorage {
        return LocalSecurityStorage()
    }

    @Bean
    @Primary
    @Conditional(RedisCondition.Enabled::class)
    open fun redisSecurityStorage(jedisPool: JedisPool) : SecurityStorage {
        return RedisSecurityStorage(jedisPool)
    }
}