package com.aldebaran.auth.core


open class JwtConfigurationException : RuntimeException {

    constructor() : super()
    constructor(message: String) : super(message)
}