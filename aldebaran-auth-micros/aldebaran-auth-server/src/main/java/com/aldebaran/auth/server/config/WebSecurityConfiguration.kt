package com.aldebaran.auth.server.config

import com.aldebaran.auth.core.authentication.UsernamePasswordAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
open class WebSecurityConfiguration() : WebSecurityConfigurerAdapter() {

    @Autowired
    fun configureAuthenticationManager(managerBuilder: AuthenticationManagerBuilder,
                                       usernamePasswordProvider: UsernamePasswordAuthenticationProvider) {
        managerBuilder
                .authenticationProvider(usernamePasswordProvider)
    }

    override fun configure(http: HttpSecurity) {
        http
            .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/oauth2/**")
    }
}