package com.aldebaran.auth.api.service

import com.aldebaran.auth.core.model.UserRegistrationRequest


interface UserService {
    fun register(request: UserRegistrationRequest)
}
