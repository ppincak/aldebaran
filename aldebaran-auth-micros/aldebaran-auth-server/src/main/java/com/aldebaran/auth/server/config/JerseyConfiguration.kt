package com.aldebaran.auth.server.config

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component
import javax.ws.rs.ApplicationPath


@Component
@ApplicationPath("/")
open class JerseyConfiguration : ResourceConfig {

    constructor() : super() {
        packages("com.aldebaran.rest",
                 "com.aldebaran.auth.api")
    }
}