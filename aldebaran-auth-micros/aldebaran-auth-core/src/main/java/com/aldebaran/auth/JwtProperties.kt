package com.aldebaran.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
data class JwtProperties(

    @Value("\${jwt.issuer}")
    val issuer: String,

    @Value("\${jwt.signature.key}")
    val signatureKey: String,

    @Value("\${jwt.encryption.key}")
    val encryptionKey: String,

    @Value("\${jwt.defaultTokenLife}")
    val defaultTokenLife: String
)