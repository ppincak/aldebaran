package com.aldebaran.auth.core.storage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
open class SecurityStorageProxy
      @Autowired constructor(val securityStorage: SecurityStorage) : SecurityStorage {

    override fun revoke(jti: String) {
        securityStorage.revoke(jti)
    }

    override fun isRevoked(jti: String) : Boolean {
        return securityStorage.isRevoked(jti)
    }
}