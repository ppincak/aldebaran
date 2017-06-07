package com.aldebaran.auth.core


open class JwtCreationException : RuntimeException {

    constructor() : super()
    constructor(message: String) : super(message)
}