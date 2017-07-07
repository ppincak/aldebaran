package com.aldebaran.chassis.monitoring;

import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.metric.consumer.HystrixDashboardStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class HystrixMetrics {

    @Autowired
    private Environment props;

    @Bean
    public TrTest testing() {
        HystrixDashboardStream
            .getInstance()
            .observe()
            .subscribe((val) -> {
                for(HystrixCommandMetrics metrics: val.getCommandMetrics()) {
                    System.out.println(metrics.getTotalTimeMean());
                    System.out.println(metrics.getExecutionTimeMean());
                    System.out.println(metrics.getCumulativeCount(HystrixEventType.SUCCESS));
                }
            });
        return null;
    }

    public static class TrTest {

    }
}