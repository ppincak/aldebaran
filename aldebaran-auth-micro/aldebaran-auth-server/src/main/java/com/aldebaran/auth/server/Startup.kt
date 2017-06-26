package com.aldebaran.auth.server

import com.aldebaran.auth.server.config.SpringConfiguration
import io.prometheus.client.hotspot.DefaultExports
import org.springframework.boot.SpringApplication


fun main(args: Array<String>) {
    DefaultExports.initialize()

    SpringApplication.run(SpringConfiguration::class.java)
}
