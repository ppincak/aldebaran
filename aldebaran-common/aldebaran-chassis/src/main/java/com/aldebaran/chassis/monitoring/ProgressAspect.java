package com.aldebaran.chassis.monitoring;

import io.prometheus.client.Gauge;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
public class ProgressAspect extends AbstractMonitoringAspect<Gauge>{

    @Pointcut("(@annotation(com.aldebaran.chassis.monitoring.Progress))")
    void annotatedWithGauche() {}

    @Around("annotatedWithGauche()")
    public Object gauge(ProceedingJoinPoint joinPoint) throws Throwable {
        Progress progress = AnnotationHelper.getAnnotation(joinPoint, Progress.class);
        Gauge gauge =
               monitors.computeIfAbsent(
                       progress.name(),
                       k -> Gauge.build(progress.name(),
                                        progress.help()).register());
        try {
            gauge.inc(progress.inc());
            return joinPoint.proceed();
        } finally {
            gauge.dec(progress.inc());
        }
    }
}