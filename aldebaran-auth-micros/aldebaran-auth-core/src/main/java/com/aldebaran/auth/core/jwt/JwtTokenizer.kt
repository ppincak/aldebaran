package com.aldebaran.auth.core.jwt

import com.aldebaran.security.authentication.AuthenticatedUser
import com.aldebaran.security.authentication.JwtAuthenticatedUser
import com.aldebaran.security.jwt.TokenUtils
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers
import org.jose4j.jws.AlgorithmIdentifiers
import org.jose4j.jws.JsonWebSignature
import org.jose4j.jwt.JwtClaims
import org.jose4j.jwt.NumericDate
import org.jose4j.jwt.consumer.InvalidJwtException
import org.jose4j.keys.AesKey
import org.jose4j.keys.HmacKey
import org.jose4j.lang.JoseException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.UnsupportedEncodingException

// JWT fields
private const val USER_ID = "uid"
private const val CLIENT_ID = "auid"
private const val USERNAME = "usrn"
private const val EMAIL = "eml"
private const val ROLES = "rls"
private const val SCOPES = "scps"

@Component
open class JwtTokenizer {

    private lateinit var signatureKey: HmacKey
    private lateinit var encryptionKey: AesKey

    private lateinit var jwtProperties: JwtProperties

    @Autowired
    fun setJwtProperties(jwtProperties: JwtProperties) {
        this.jwtProperties = jwtProperties
        try {
            signatureKey = HmacKey(jwtProperties.signatureKey.toByteArray())
            encryptionKey = AesKey(jwtProperties.encryptionKey.toByteArray())
        } catch (e: UnsupportedEncodingException) {
            throw JwtConfigurationException("Failed to create signature and encryption keys")
        }
    }

    fun generateToken(authenticatedUser: AuthenticatedUser, jti: String, expireIn: Long) : String {
        val claims = JwtClaims()
        claims.issuer = jwtProperties?.issuer
        claims.jwtId = jti

        claims.setClaim(USER_ID, authenticatedUser.userId)
        claims.setClaim(CLIENT_ID, authenticatedUser.clientId)
        claims.setClaim(USERNAME, authenticatedUser.username)
        claims.setClaim(EMAIL, authenticatedUser.email)
        claims.setClaim(SCOPES, authenticatedUser.scopes)
        claims.setClaim(ROLES, TokenUtils.extractRoles(authenticatedUser.authorities))

        val time = System.currentTimeMillis()
        claims.expirationTime = NumericDate.fromMilliseconds(time + expireIn)
        claims.issuedAt = NumericDate.fromMilliseconds(time)

        val jws = JsonWebSignature()
        jws.payload = claims.toJson()
        jws.key = signatureKey
        jws.algorithmHeaderValue = AlgorithmIdentifiers.HMAC_SHA512

        var jwt: String
        try {
            jwt = jws.compactSerialization

            if (jwtProperties.encrypt) {
                val jwe = JsonWebEncryption()
                jwe.payload = jwt
                jwe.key = encryptionKey
                jwe.algorithmHeaderValue = KeyManagementAlgorithmIdentifiers.DIRECT
                jwe.encryptionMethodHeaderParameter = ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512
                jwt = jwe.compactSerialization
            }
        } catch (e: JoseException) {
            throw JwtCreationException("Failed to create jwt token")
        }
        return jwt
    }

    fun getClaims(jwt: String) : JwtClaims {
        val decryptedToken: String

        if(jwtProperties.encrypt) {
            val jwe = JsonWebEncryption()

            try {
                jwe.compactSerialization = jwt
                jwe.key = encryptionKey
                decryptedToken = jwe.plaintextString
            } catch (e: JoseException) {
                throw JwtVerificationException("Failed to decrypt token")
            }
        } else {
            decryptedToken = jwt
        }

        val payload: String
        try {
            val jws = JsonWebSignature()
            jws.compactSerialization = decryptedToken
            jws.key = signatureKey
            payload = jws.payload
            return JwtClaims.parse(payload)
        } catch (e: JoseException) {
            throw JwtVerificationException("Failed to verify token signature")
        } catch (e: InvalidJwtException) {
            throw JwtVerificationException("Invalid jwt")
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun getAuthenticatedUser(token: String): JwtAuthenticatedUser {
        val claims = getClaims(token)

        val authenticatedUser = JwtAuthenticatedUser()
        authenticatedUser.jti = claims.jwtId
        authenticatedUser.userId = claims.getClaimValue(USER_ID) as Long
        authenticatedUser.clientId = claims.getClaimValue(CLIENT_ID) as String
        authenticatedUser.username = claims.getClaimValue(USERNAME) as String
        authenticatedUser.email = claims.getClaimValue(EMAIL) as String
        authenticatedUser.scopes = claims.getClaimValue(SCOPES) as Collection<String>
        authenticatedUser.authorities = TokenUtils.createAuthorities(claims.getClaimValue(ROLES))
        authenticatedUser.expiresAt = claims.expirationTime.value
        return authenticatedUser
    }
}