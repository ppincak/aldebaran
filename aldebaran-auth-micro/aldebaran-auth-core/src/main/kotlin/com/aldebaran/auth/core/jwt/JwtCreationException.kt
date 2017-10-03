package com.aldebaran.auth.core.jwt


open class JwtCreationException : RuntimeException {

    constructor() : super()
    constructor(message: String) : super(message)
}