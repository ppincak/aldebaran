package com.aldebaran.chassis.discovery;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


public class ConsulCondition implements Condition {

    private final String value;

    protected ConsulCondition(String value) {
        this.value = value;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return null != env && value.equals(env.getProperty("discovery.consul.enabled"));
    }

    public static class Enabled extends ConsulCondition {
        public Enabled() {
            super("true");
        }
    }

    public static class Disabled extends ConsulCondition {
        public Disabled() {
            super("false");
        }
    }
}
