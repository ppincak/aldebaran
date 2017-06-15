package com.aldebaran.auth.core.jwt


class JwtVerificationException : RuntimeException {

    constructor() : super()
    constructor(message: String) : super(message)
}