package com.aldebaran.chassis.monitoring;

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
        //PushGateway pushGateway = new PushGateway("");

        HystrixDashboardStream
                .getInstance()
                .observe()
                .subscribe((val) -> {
                    System.out.println("data emitted");
                });
        return null;
    }

    public static class TrTest {

    }
}