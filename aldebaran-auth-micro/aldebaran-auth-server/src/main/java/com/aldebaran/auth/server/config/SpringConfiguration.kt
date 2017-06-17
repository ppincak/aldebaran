package com.aldebaran.auth.server.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


const val componentScan = "com.aldebaran";

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableJpaRepositories(value = componentScan)
@ComponentScan(componentScan)
open class SpringConfiguration {

    @Bean
    open fun messageSource(): ReloadableResourceBundleMessageSource {
        val source = ReloadableResourceBundleMessageSource()
        source.setBasenames(
                "classpath:i18n/messages",
                "classpath:i18n/general_messages")
        source.setDefaultEncoding("UTF-8")
        return source
    }
}