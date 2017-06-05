package com.aldebaran.config

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component
import javax.ws.rs.ApplicationPath


@Component
@ApplicationPath("/")
open class JerseyConfiguration : ResourceConfig {

    constructor() : super() {
        packages(componentScan)
    }
}