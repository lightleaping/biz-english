package com.lightleaping.bizenglish

import com.lightleaping.bizenglish.application.AuthService
import com.lightleaping.bizenglish.application.InvalidCredentialsException
import com.lightleaping.bizenglish.domain.UserRepository
import com.lightleaping.bizenglish.infra.InMemoryUserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AuthServiceTest {
    private val repo: UserRepository = InMemoryUserRepository()
    private val sut = AuthService(repo)

    @Test
    fun `signup then login succeeds`() {
        val u = sut.signup("a@b.com", "pw1234!", "Alice")
        assertTrue(u.id > 0)
        val logged = sut.login("a@b.com", "pw1234!")
        assertEquals(u.id, logged.id)
    }

    @Test
    fun `login fails with wrong password`() {
        sut.signup("x@y.com", "right-pass", "X")
        assertThrows(InvalidCredentialsException::class.java) {
            sut.login("x@y.com", "wrong-pass")
        }
    }
}
