package com.aldebaran.auth.core.enums

import com.aldebaran.utils.descriptors.ValueEnum


enum class TokenType(val label: String, val enumValue: String) : ValueEnum<String> {
    BEARER("bearer", "bearer");

    override fun getValue(): String {
        return enumValue
    }
}