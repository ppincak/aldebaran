package com.aldebaran.auth.core.details

import com.aldebaran.security.api.Checker
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


@Component
open class UserDetailsChecker : Checker<UserDetails> {

    override fun check(user: UserDetails) {
        if (!user.isAccountNonLocked) {
            throw LockedException(null)
        }
        if (!user.isEnabled) {
            throw DisabledException(null)
        }
        if (!user.isAccountNonExpired) {
            throw AccountExpiredException(null)
        }
        if (!user.isCredentialsNonExpired) {
            throw CredentialsExpiredException(null)
        }
    }
}