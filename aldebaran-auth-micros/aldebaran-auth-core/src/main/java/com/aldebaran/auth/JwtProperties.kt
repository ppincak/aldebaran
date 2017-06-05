package com.aldebaran.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
data class JwtProperties(

    @Value("\${jwt.issuer}")
    var issuer: String,

    @Value("\${jwt.signature.key}")
    var signatureKey: String,

    @Value("\${jwt.encryption.key}")
    var encryptionKey: String,

    @Value("\${jwt.defaultTokenLife}")
    var defaultTokenLife: String
)