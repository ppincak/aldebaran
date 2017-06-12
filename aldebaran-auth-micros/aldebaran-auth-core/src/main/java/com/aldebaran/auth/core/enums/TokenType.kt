package com.aldebaran.auth.core.enums

import com.aldebaran.utils.ValueEnum


enum class TokenType(val label: String, val enumValue: String) : ValueEnum<String> {
    BEARER("bearer", "Bearer");

    override fun getValue(): String {
        return enumValue
    }
}