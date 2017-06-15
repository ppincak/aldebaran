package com.aldebaran.auth.core.storage


interface SecurityStorage {

    fun revoke(jti: String)
    fun isRevoked(jti: String) : Boolean
}