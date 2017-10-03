package com.aldebaran.redis;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


public abstract class RedisCondition implements Condition {

    private final String value;

    private RedisCondition(String value) {
        this.value = value;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env != null && value.equals(env.getProperty("redis.enabled"));
    }

    public static class Enabled extends RedisCondition {
        public Enabled() {
            super("true");
        }
    }

    public static class Disabled extends RedisCondition {
        public Disabled() {
            super("false");
        }
    }
}
