package com.aldebaran.auth


open class JwtCreationException : RuntimeException {

    constructor() : super() {

    }

    constructor(message: String) : super(message) {

    }
}