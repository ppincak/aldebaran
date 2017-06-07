package com.aldebaran.auth.controller

import com.aldebaran.auth.core.GrantType
import com.aldebaran.auth.core.model.TokenRequest
import com.aldebaran.auth.core.model.TokenResponse
import com.aldebaran.security.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service


@Service
open class Oauth2ServiceImpl
    @Autowired constructor(val authManager: AuthenticationManager) : OAuth2Service {

    override fun token(tokenRequest: TokenRequest): TokenResponse {
        when (tokenRequest.getGrantType()){
            GrantType.PASSWORD -> return tokenGrant(tokenRequest)
            GrantType.AUTHORIZATION_CODE -> TODO()
            GrantType.CLIENT_CREDENTIALS -> TODO()
            GrantType.REFRESH_TOKEN -> TODO()
            null -> throw RuntimeException();
        }
    }

    override fun tokenInfo(tokenRequest: TokenRequest): AuthenticatedUser {
        TODO("not implemented")
    }

    private fun tokenGrant(tokenRequest: TokenRequest) : TokenResponse {
        val authentication = authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        tokenRequest.username,
                        tokenRequest.password
                )
        )

        

        throw RuntimeException();
    }
}