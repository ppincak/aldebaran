package com.aldebaran.auth.core.storage


open class RedisSecurityStorage : SecurityStorage {

    override fun revoke(jti: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isRevoked(jti: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}