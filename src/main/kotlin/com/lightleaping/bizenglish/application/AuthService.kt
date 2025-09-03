package com.lightleaping.bizenglish.application

import com.lightleaping.bizenglish.domain.User
import com.lightleaping.bizenglish.domain.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

class EmailAlreadyUsedException: RuntimeException("Email already used")
class InvalidCredentialsException: RuntimeException("Invalid email or password")

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
) {
    fun signup(email: String, rawPassword: String, displayName: String): User {
        if (userRepository.findByEmail(email) != null) throw EmailAlreadyUsedException()
        val hash = passwordEncoder.encode(rawPassword)
        return userRepository.save(User(id = 0L, email = email, passwordHash = hash, displayName = displayName))
    }

    fun login(email: String, rawPassword: String): User {
        val user = userRepository.findByEmail(email) ?: throw InvalidCredentialsException()
        if (!passwordEncoder.matches(rawPassword, user.passwordHash)) throw InvalidCredentialsException()
        return user
    }
}