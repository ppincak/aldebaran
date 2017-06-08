package com.aldebaran.auth.core.storage

import org.springframework.stereotype.Component


@Component
open class SecurityStorageProxy(val securityStorage: SecurityStorage) : SecurityStorage {

    override fun revoke(jti: String) {
        securityStorage.revoke(jti)
    }

    override fun isRevoked(jti: String) : Boolean {
        return securityStorage.isRevoked(jti)
    }
}