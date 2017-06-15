package com.aldebaran.auth.core.jwt


open class JwtConfigurationException : RuntimeException {

    constructor() : super()
    constructor(message: String) : super(message)
}