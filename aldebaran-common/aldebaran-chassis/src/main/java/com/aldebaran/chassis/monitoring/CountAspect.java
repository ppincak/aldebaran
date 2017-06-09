package com.aldebaran.chassis.monitoring;

import io.prometheus.client.Counter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CountAspect extends AbstractMonitoringAspect<Counter> {

    @Pointcut("@annotation(com.aldebaran.chassis.monitoring.Count)")
    void annotatedWithCount() {}

    @Before("(annotatedWithCount())")
    public void count(JoinPoint joinPoint) {
        Count count = AnnotationHelper.getAnnotation(joinPoint, Count.class);
        Counter counter =
                monitors.computeIfAbsent(
                        count.name(),
                        k -> Counter.build(count.name(),
                                           count.help()).create().register());
        counter.inc(count.incBy());
    }
}