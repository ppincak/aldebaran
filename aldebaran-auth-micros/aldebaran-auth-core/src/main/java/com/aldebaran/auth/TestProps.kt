package com.aldebaran.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
open class TestProps {

    @Value("\${jwt.issuer}")
    var issuer: String = ""
    set(value) {
        field = value
    }

    @Value("\${jwt.signature.key}")
    var signatureKey: String = ""
    set(value) {
        field = value
    }

    @Value("\${jwt.encryption.key}")
    var encryptionKey: String = ""
    set(value) {
        field = value
    }

    @Value("\${jwt.defaultTokenLife}")
    var defaultTokenLife: String =""
    set(value) {
        field = value
    }
}