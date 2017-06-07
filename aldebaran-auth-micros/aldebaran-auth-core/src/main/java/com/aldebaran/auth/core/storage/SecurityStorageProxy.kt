package com.aldebaran.auth.core.storage

import org.springframework.stereotype.Component


@Component
open class SecurityStorageProxy : SecurityStorage {

    override fun revoke(jti: String) {

    }

    override fun isRevoked(jti: String) : Boolean {
        return false
    }
}