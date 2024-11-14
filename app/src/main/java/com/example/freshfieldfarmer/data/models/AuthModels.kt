package com.example.freshfieldfarmer.data.models

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val userId: Int
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val farmName: String
)
