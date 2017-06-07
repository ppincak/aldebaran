package com.aldebaran.auth.controller

import com.aldebaran.auth.core.model.TokenRequest
import com.aldebaran.auth.core.model.TokenResponse
import com.aldebaran.security.AuthenticatedUser


interface OAuth2Service {

    fun token(tokenRequest: TokenRequest) : TokenResponse

    fun tokenInfo(tokenRequest: TokenRequest) : AuthenticatedUser
}