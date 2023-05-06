package com.rodrigomaldonadov.jwtsecurity.controller

import com.rodrigomaldonadov.jwtsecurity.jwt.TokenService
import com.rodrigomaldonadov.jwtsecurity.model.UserLogin
import com.rodrigomaldonadov.jwtsecurity.model.UserModel
import com.rodrigomaldonadov.jwtsecurity.service.PwdHashService
import com.rodrigomaldonadov.jwtsecurity.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api")
class AuthController (
    private val pwdHashService: PwdHashService,
    private val tokenService: TokenService,
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody payload: UserLogin): String {
        val user = userService.findByName(payload.username) ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Login failed")

        if (!pwdHashService.checkBcrypt(payload.password, user.password)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Login failed")
        }

        return tokenService.createToken(user)
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: UserLogin): String {

        if (userService.existsByName(payload.username)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists")
        }

        val user = UserModel(
            username = payload.username,
            password = pwdHashService.hashBcrypt(payload.password)
        )

        val savedUserModel = userService.save(user)

        return tokenService.createToken(savedUserModel)
    }
}