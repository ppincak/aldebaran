package com.aldebaran.auth.core.repository

import com.aldebaran.auth.core.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun getById(id: Long): User

    fun getByUsername(username: String): User?

    fun getByEmail(email: String): User
}