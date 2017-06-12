package com.aldebaran.server.config;

import com.aldebaran.chassis.discovery.ServiceDescription;
import com.aldebaran.chassis.discovery.ServiceDiscovery;
import com.aldebaran.chassis.discovery.ServiceDiscoveryStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class
})
@EnableJpaRepositories(value = SpringConfiguration.jpaRepositories)
@ComponentScan(basePackages =  SpringConfiguration.componentScan)
public class SpringConfiguration {

    /** Package for component scanning */
    public static final String componentScan = "com.aldebaran";

    /** Package for jpa repositories  */
    public static final String jpaRepositories = "com.aldebaran";

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ServiceDiscovery serviceDiscovery() {
        Map<String, ServiceDescription> services = new HashMap<>();
        return new ServiceDiscoveryStub(services);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source =
                new ReloadableResourceBundleMessageSource();
        source.setBasenames(
                "classpath:i18n/messages",
                "classpath:i18n/general_messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}