package com.example.todolistapp.models

data class UserResponse(
    val data: UserModel,
    val errors: String
)

data class UserModel (
    val username: String,
    val token: String?
)