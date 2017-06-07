package com.aldebaran.auth.core


class JwtVerificationException : RuntimeException {

    constructor() : super()
    constructor(message: String) : super(message)
}