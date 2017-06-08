package com.aldebaran.auth.core.details

import com.aldebaran.auth.core.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


data class DbAuthenticatedUser(val user: User) : UserDetails {

    fun getUserEntity() : User {
        return user
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return ArrayList<GrantedAuthority>(0)
    }

    override fun isEnabled(): Boolean {
        return user.enabled
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return !user.credentialsExpired
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return !user.expired
    }

    override fun isAccountNonLocked(): Boolean {
        return !user.locked
    }
}