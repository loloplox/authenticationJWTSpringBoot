package com.rodrigomaldonadov.jwtsecurity.repository

import com.rodrigomaldonadov.jwtsecurity.model.UserModel
import org.springframework.data.mongodb.repository.MongoRepository


interface UserRepository : MongoRepository<UserModel, String> {

    fun findByUsername(name: String): UserModel?
    fun existsByUsername(name: String): Boolean
}