package com.aldebaran.redis;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


public class RedisDisabledCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env != null && "false".equals(env.getProperty("redis.enabled"));
    }
}