package com.rodrigomaldonadov.jwtsecurity.service

import com.rodrigomaldonadov.jwtsecurity.model.UserModel
import com.rodrigomaldonadov.jwtsecurity.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findById(id: String): UserModel? {
        return userRepository.findByIdOrNull(id)
    }

    fun findByName(name: String): UserModel? {
        return userRepository.findByUsername(name)
    }

    fun existsByName(name: String): Boolean {
        return userRepository.existsByUsername(name)
    }

    fun save(user: UserModel): UserModel {
        return userRepository.save(user)
    }
}