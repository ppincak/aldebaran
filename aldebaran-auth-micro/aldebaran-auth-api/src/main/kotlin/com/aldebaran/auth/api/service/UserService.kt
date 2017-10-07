package com.aldebaran.auth.api.service

import com.aldebaran.auth.core.model.UserRegistrationRequest
import com.aldebaran.auth.core.model.UserRegistrationResponse


interface UserService {
    fun register(request: UserRegistrationRequest): UserRegistrationResponse
}
