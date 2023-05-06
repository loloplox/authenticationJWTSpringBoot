package com.rodrigomaldonadov.jwtsecurity.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class UserModel (
    @Id
    val id: ObjectId = ObjectId(),
    val username: String,
    val password: String,
)

data class UserLogin (
    val username: String,
    val password: String
)