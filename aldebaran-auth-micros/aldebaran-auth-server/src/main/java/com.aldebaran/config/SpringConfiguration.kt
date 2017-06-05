package com.aldebaran.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy


const val componentScan = "com.aldebaran";

@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(componentScan)
open class SpringConfiguration {



}