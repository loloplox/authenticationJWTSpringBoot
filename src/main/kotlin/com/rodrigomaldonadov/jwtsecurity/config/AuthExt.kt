package com.rodrigomaldonadov.jwtsecurity.config

import com.rodrigomaldonadov.jwtsecurity.model.UserModel
import org.springframework.security.core.Authentication

fun Authentication.toUser(): UserModel {
    return this.principal as UserModel
}