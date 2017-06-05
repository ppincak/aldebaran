package com.aldebaran.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
open class WebSecurityConfiguration
        @Autowired constructor(userDetailsService: UserDetailsService)
            : WebSecurityConfigurerAdapter() {

    @Bean
    open fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder();
    }

    override fun configure(http: HttpSecurity?) {
        super.configure(http)
    }

    override fun configure(web: WebSecurity?) {
        super.configure(web)
    }
}