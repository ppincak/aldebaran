package com.aldebaran.auth.service

import com.aldebaran.auth.core.GrantType
import com.aldebaran.auth.core.jwt.JwtTokenizer
import com.aldebaran.auth.core.details.DbAuthenticatedUser
import com.aldebaran.auth.core.model.TokenRequest
import com.aldebaran.auth.core.model.TokenResponse
import com.aldebaran.security.AuthenticatedUser
import com.aldebaran.security.JwtAuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service


@Service
open class Oauth2ServiceImpl
    @Autowired constructor(val authManager: AuthenticationManager,
                           val jwtTokenizer: JwtTokenizer) : OAuth2Service {

    override fun token(tokenRequest: TokenRequest): TokenResponse {
        when (tokenRequest.getGrantType()){
            GrantType.PASSWORD -> return tokenGrant(tokenRequest)
            GrantType.AUTHORIZATION_CODE -> TODO()
            GrantType.CLIENT_CREDENTIALS -> TODO()
            GrantType.REFRESH_TOKEN -> TODO()
            null -> throw RuntimeException();
        }
    }

    override fun tokenInfo(token: String): AuthenticatedUser {
        TODO("not implemented")
    }

    private fun tokenGrant(tokenRequest: TokenRequest) : TokenResponse {
        val authentication = authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        tokenRequest.username,
                        tokenRequest.password
                )
        )

        val userDetails = authentication.principal as DbAuthenticatedUser


        throw RuntimeException();
    }

    private fun generateAccessToken(userDetails: DbAuthenticatedUser): String {
        val authenticatedUser = getJwtAuthenticatedUser(userDetails)

       // val jti = TokenUtils.generateJti(userDetails.getUser().getTempTokenId())
        val jti = ""

        return jwtTokenizer.generateToken(authenticatedUser, jti, 100L, true)
    }

    private fun getJwtAuthenticatedUser(userDetails: DbAuthenticatedUser): JwtAuthenticatedUser {
        val user = userDetails.getUserEntity()

        val authenticatedUser = JwtAuthenticatedUser()
        authenticatedUser.setUserId(user.getId())
        authenticatedUser.setUsername(user.getUsername())
        authenticatedUser.setEmail(user.getEmail())
        authenticatedUser.setAuthorities(userDetails.getAuthorities())
        return authenticatedUser
    }
}