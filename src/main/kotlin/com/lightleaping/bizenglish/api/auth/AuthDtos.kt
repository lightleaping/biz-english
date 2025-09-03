package com.lightleaping.bizenglish.api.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignupRequest(
    @field:Email val email: String,
    @field:NotBlank val password: String,
    @field:NotBlank val displayName: String
)

data class LoginRequest(
    @field:Email val email: String,
    @field:NotBlank val password: String
)

data class AuthResponse(
    val accessToken: String, // mock UUID
    val userId: Long,
    val displayName: String
)