package com.lightleaping.bizenglish.api.auth

import com.lightleaping.bizenglish.application.AuthService
import com.lightleaping.bizenglish.application.EmailAlreadyUsedException
import com.lightleaping.bizenglish.application.InvalidCredentialsException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody req: SignupRequest): ResponseEntity<AuthResponse> {
        val user = authService.signup(req.email, req.password, req.displayName)
        val token = UUID.randomUUID().toString() // MOCK
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(AuthResponse(token, user.id, user.displayName))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody req: LoginRequest): ResponseEntity<AuthResponse> {
        val user = authService.login(req.email, req.password)
        val token = UUID.randomUUID().toString() // MOCK
        return ResponseEntity.ok(AuthResponse(token, user.id, user.displayName))
    }

    @ExceptionHandler(EmailAlreadyUsedException::class)
    fun handleEmailUsed(): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("error" to "EMAIL_IN_USE"))

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCreds(): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapOf("error" to "INVALID_CREDENTIALS"))
}
