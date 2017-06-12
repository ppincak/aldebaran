package com.aldebaran.auth.api.service

import com.aldebaran.auth.core.model.TokenRevokeRequest
import com.aldebaran.auth.core.storage.SecurityStorageProxy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
open class SecurityServiceImpl
    @Autowired constructor(val storageProxy: SecurityStorageProxy): SecurityService {

    override fun revoke(tokenRevokeRequest: TokenRevokeRequest) {
        for(jti: String in tokenRevokeRequest.jtis) {
            storageProxy.revoke(jti)
        }
    }
}