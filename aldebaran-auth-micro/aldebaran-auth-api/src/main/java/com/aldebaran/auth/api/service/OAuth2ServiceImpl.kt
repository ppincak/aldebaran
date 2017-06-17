package com.aldebaran.auth.api.service

import com.aldebaran.auth.api.AuthErrorEvents
import com.aldebaran.auth.core.authentication.UsernamePasswordAuthentication
import com.aldebaran.auth.core.enums.GrantType
import com.aldebaran.auth.core.jwt.JwtTokenizer
import com.aldebaran.auth.core.details.DbAuthenticatedUser
import com.aldebaran.auth.core.enums.TokenType
import com.aldebaran.auth.core.jwt.JwtProperties
import com.aldebaran.auth.core.jwt.JwtVerificationException
import com.aldebaran.auth.core.model.TokenRequest
import com.aldebaran.auth.core.model.TokenResponse
import com.aldebaran.auth.core.storage.SecurityStorageProxy
import com.aldebaran.rest.error.GeneralErrorEvents
import com.aldebaran.rest.error.event.ApplicationException
import com.aldebaran.rest.error.event.ErrorEvent
import com.aldebaran.security.authentication.JwtAuthenticatedUser
import com.aldebaran.security.jwt.TokenInfo
import com.aldebaran.security.jwt.TokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service


@Service
open class OAuth2ServiceImpl
    @Autowired constructor(val authManager: AuthenticationManager,
                           val storageProxy: SecurityStorageProxy,
                           val jwtProperties: JwtProperties,
                           val jwtTokenizer: JwtTokenizer) : OAuth2Service {

    override fun token(tokenRequest: TokenRequest): TokenResponse {
        when (tokenRequest.getGrantType()){
            GrantType.PASSWORD -> return tokenGrant(tokenRequest)
            else -> {
                throw ApplicationException(AuthErrorEvents.UNSUPPORTED_GRANT_TYPE)
            }
        }
    }

    override fun tokenInfo(authorizationHeader: String): TokenInfo {
        val jwt = TokenUtils.extractJwt(authorizationHeader, TokenType.BEARER.enumValue)
        if(jwt == null) {
            throw ApplicationException(GeneralErrorEvents.UNAUTHORIZED)
        }
        try {
            val authenticatedUser = jwtTokenizer.getAuthenticatedUser(jwt)
            if(storageProxy.isRevoked(authenticatedUser.jti)) {
                throw ApplicationException(GeneralErrorEvents.UNAUTHORIZED)
            }
            return TokenInfo(authenticatedUser)
        } catch(e: JwtVerificationException) {
            throw ApplicationException(GeneralErrorEvents.UNAUTHORIZED)
        }
    }

    private fun tokenGrant(tokenRequest: TokenRequest) : TokenResponse {
        var authentication : Authentication

        try {
            authentication = authManager.authenticate(
                    UsernamePasswordAuthentication(
                            tokenRequest.username,
                            tokenRequest.password
                    )
            )
        } catch (e: Exception) {
            var errorEvent: ErrorEvent;

            when(e) {
                is LockedException -> {
                    errorEvent = AuthErrorEvents.USER_LOCKED
                }
                is DisabledException -> {
                    errorEvent = AuthErrorEvents.USER_DISABLED
                }
                is AccountExpiredException -> {
                    errorEvent = AuthErrorEvents.USER_EXPIRED
                }
                is CredentialsExpiredException -> {
                    errorEvent = AuthErrorEvents.USER_CREDENTIALS_EXPIRED
                }
                else -> {
                    errorEvent = AuthErrorEvents.BAD_CREDENTIALS
                }
            }
            throw ApplicationException(errorEvent);
        }

        val userDetails = authentication.principal as DbAuthenticatedUser
        val authenticatedUser = generateAccessToken(userDetails, "clientId")

        return TokenResponse(authenticatedUser.jwt,
                             null,
                             TokenType.BEARER,
                             authenticatedUser.scopes,
                             authenticatedUser.expiresAt)
    }

    private fun generateAccessToken(userDetails: DbAuthenticatedUser, clientId: String): JwtAuthenticatedUser {
        val authenticatedUser = getJwtAuthenticatedUser(userDetails, clientId)
        val jti = TokenUtils.generateJti(authenticatedUser);
        val jwt = jwtTokenizer
                    .generateToken(authenticatedUser, jti, jwtProperties.defaultTokenLife)
        authenticatedUser.jwt = jwt
        authenticatedUser.jti = jti
        return authenticatedUser
    }

    private fun getJwtAuthenticatedUser(userDetails: DbAuthenticatedUser, clientId: String): JwtAuthenticatedUser {
        val user = userDetails.getUserEntity()

        val authenticatedUser = JwtAuthenticatedUser()
        authenticatedUser.userId = user.id
        authenticatedUser.username = user.username
        authenticatedUser.email = user.email
        authenticatedUser.clientId = clientId
        authenticatedUser.scopes = ArrayList<String>()
        authenticatedUser.authorities = userDetails.authorities
        return authenticatedUser
    }
}