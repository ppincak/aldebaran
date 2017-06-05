package com.aldebaran.auth


open class JwtConfigurationException : RuntimeException {

    constructor() : super() {

    }

    constructor(message: String) : super(message) {

    }
}