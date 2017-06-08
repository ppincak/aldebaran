package com.aldebaran.auth.core.details

import com.aldebaran.auth.core.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
open class UserDetailsServiceImpl
    @Autowired constructor(val userRepository: UserRepository,
                           val userDetailsChecker: UserDetailsChecker) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.getByUsername(username) ?:
                throw BadCredentialsException("Bad credentials");

        val userDetails = DbAuthenticatedUser(user)
        userDetailsChecker.check(userDetails)
        return userDetails
    }
}