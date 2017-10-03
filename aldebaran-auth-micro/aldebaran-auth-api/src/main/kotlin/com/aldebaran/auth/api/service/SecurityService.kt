package com.aldebaran.auth.api.service

import com.aldebaran.auth.core.model.TokenRevokeRequest


interface SecurityService {

    fun revoke(tokenRevokeRequest: TokenRevokeRequest)
}