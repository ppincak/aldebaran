package com.aldebaran.auth.core.storage

import java.util.concurrent.ConcurrentHashMap


open class LocalSecurityStorage : SecurityStorage {

    private val revokedJtis = ConcurrentHashMap<String, String?>()

    override fun revoke(jti: String) {
        revokedJtis.put(jti, null)
    }

    override fun isRevoked(jti: String): Boolean {
        return revokedJtis.containsKey(jti)
    }
}