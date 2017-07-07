package com.aldebaran.server.config;

import com.aldebaran.chassis.discovery.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
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
    @Conditional(ConsulCondition.Disabled.class)
    public ServiceDiscovery serviceDiscovery(Environment env) {
        String authHost = env.getProperty("discovery.aldebaran-auth.host");
        Integer authPort = env.getProperty("discovery.aldebaran-auth.port", Integer.class);

        Map<String, ServiceDescription> services = new HashMap<>();
        services.put("aldebaran-auth",
                     new ServiceDescriptionModel("http://", authHost, authPort));

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