package com.aldebaran.auth.core.storage

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
open class SecurityStorageConfiguration {

    @Bean
    @Primary
    open fun securityStorage() : SecurityStorage{
        return LocalSecurityStorage()
    }
}