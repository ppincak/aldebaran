package com.aldebaran.auth.core.jwt

import com.sun.org.apache.xpath.internal.operations.Bool
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

    @Value("\${jwt.encrypt}")
    val encrypt: Boolean,

    @Value("\${jwt.defaultTokenLife}")
    val defaultTokenLife: Long
)