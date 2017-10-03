package com.aldebaran.auth.core.enums

import com.aldebaran.utils.descriptor.ValueEnum


enum class GrantType(val grantType: String) : ValueEnum<String> {
    PASSWORD("password"),
    AUTHORIZATION_CODE("authorization_code"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");

    override fun getValue(): String {
        return grantType;
    }
}