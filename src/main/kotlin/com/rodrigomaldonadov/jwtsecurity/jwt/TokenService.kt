package com.rodrigomaldonadov.jwtsecurity.jwt

import com.rodrigomaldonadov.jwtsecurity.model.UserModel
import com.rodrigomaldonadov.jwtsecurity.service.UserService
import org.bson.types.ObjectId
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService (
    private val jwtEncoder: JwtEncoder,
    private val jwtDecoder: JwtDecoder,
    private val userService: UserService
) {

    fun createToken(user: UserModel): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.username)
            .claim("userId", user.id.toHexString())
            .build()

        println(claims.claims["userId"])

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): UserModel? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as String
            userService.findById(userId)
        } catch (e: Exception) {
            null
        }
    }
}