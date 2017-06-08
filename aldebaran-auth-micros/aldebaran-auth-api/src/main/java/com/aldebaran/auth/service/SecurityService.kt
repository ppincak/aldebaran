package com.aldebaran.auth.service


interface SecurityService {

    fun revoke(jwt: String)
}