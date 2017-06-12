package com.aldebaran.auth.server.authentication

import com.aldebaran.auth.api.service.OAuth2Service
import com.aldebaran.security.authentication.JwtAuthentication
import com.aldebaran.security.authentication.UnauthorizedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component


@Component
open class JwtAuthenticationProvider
            @Autowired constructor(val oAuth2Service: OAuth2Service) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
        val jwtAuthentication = authentication as JwtAuthentication
        try {
            oAuth2Service.tokenInfo(jwtAuthentication.jwt)
            jwtAuthentication.isAuthenticated = true
        } catch (e: Exception) {
            throw UnauthorizedException();
        }
        return jwtAuthentication
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication!!.isAssignableFrom(JwtAuthentication::class.java)
    }
}