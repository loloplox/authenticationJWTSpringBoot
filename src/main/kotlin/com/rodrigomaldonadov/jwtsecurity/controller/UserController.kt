package com.rodrigomaldonadov.jwtsecurity.controller

import com.rodrigomaldonadov.jwtsecurity.config.toUser
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class UserController {

    @GetMapping("/")
    fun someRequest(authentication: Authentication): String {
        val authUser = authentication.toUser()
        return "Hello ${authUser.username}"
    }
}