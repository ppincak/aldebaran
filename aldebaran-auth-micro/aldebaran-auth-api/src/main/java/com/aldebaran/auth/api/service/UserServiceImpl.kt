package com.aldebaran.auth.api.service

import com.aldebaran.auth.api.AuthErrorEvents
import com.aldebaran.auth.core.entity.User
import com.aldebaran.auth.core.model.UserRegistrationRequest
import com.aldebaran.auth.core.repository.UserRepository
import com.aldebaran.rest.error.event.ApplicationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
open class UserServiceImpl
        @Autowired constructor(val userRepository: UserRepository,
                               val passwordEncoder: PasswordEncoder): UserService {

    override fun register(request: UserRegistrationRequest) {
        if(request.password!!.toLowerCase().equals(request.repeatPassword!!.toLowerCase()) == false) {
            throw ApplicationException(AuthErrorEvents.PASSWORDS_DO_NOT_MATCH)
        }
        if(userRepository.countByUsername(request.username!!) > 0) {
            throw ApplicationException(AuthErrorEvents.USERNAME_ALREADY_TAKEN)
        }
        if(userRepository.countByEmail(request.email!!) > 0) {
            throw ApplicationException(AuthErrorEvents.EMAIL_ALREADY_TAKEN)
        }

        val user = User()
        user.username = request.username
        user.email = request.email
        user.password = passwordEncoder.encode(request.password)
        user.locked = false
        user.enabled = true
        user.credentialsExpired = false
        user.expired = false

        userRepository.save(user)
    }
}