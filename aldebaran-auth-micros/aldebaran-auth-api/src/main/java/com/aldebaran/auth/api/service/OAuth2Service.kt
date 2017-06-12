package com.aldebaran.auth.api.service

import com.aldebaran.auth.core.model.TokenRequest
import com.aldebaran.auth.core.model.TokenResponse
import com.aldebaran.security.jwt.TokenInfo


interface OAuth2Service {

    fun token(tokenRequest: TokenRequest) : TokenResponse

    fun tokenInfo(authorizationHeader: String) : TokenInfo
}