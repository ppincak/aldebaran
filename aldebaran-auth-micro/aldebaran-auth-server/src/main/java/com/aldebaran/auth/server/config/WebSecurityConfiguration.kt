package com.aldebaran.auth.server.config

import com.aldebaran.auth.core.authentication.UsernamePasswordAuthenticationProvider
import com.aldebaran.auth.server.authentication.JwtAuthenticationProvider
import com.aldebaran.security.authentication.filter.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class WebSecurityConfiguration() : WebSecurityConfigurerAdapter() {

    @Autowired
    private val jwtAuthenticationFilter: JwtAuthenticationFilter? = null

    @Autowired
    fun configureAuthenticationManager(managerBuilder: AuthenticationManagerBuilder,
                                       jwtAuthenticationProvider: JwtAuthenticationProvider,
                                       usernamePasswordProvider: UsernamePasswordAuthenticationProvider) {
        managerBuilder
                .authenticationProvider(usernamePasswordProvider)
                .authenticationProvider(jwtAuthenticationProvider)
    }

    @Bean
    open fun jwtFilterRegistrationBean(filter: JwtAuthenticationFilter): FilterRegistrationBean {
        val registration = FilterRegistrationBean(filter)
        registration.isEnabled = false
        return registration
    }

    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
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
        web.ignoring().antMatchers("/oauth2/**",
                                   "/users/register",
                                   "/monitor/**")
    }
}