package com.aldebaran.auth.server.config

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.core.type.AnnotatedTypeMetadata


@Configuration
@Conditional(RedisConfiguration.RedisCondition::class)
open class RedisConfiguration {

    class RedisCondition : Condition {
        override fun matches(context: ConditionContext?,
                             metadata: AnnotatedTypeMetadata?): Boolean {
            return "true" == context?.environment?.getProperty("redis.enabled")
        }
    }
}