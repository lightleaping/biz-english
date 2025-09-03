package com.lightleaping.bizenglish

import com.lightleaping.bizenglish.api.auth.AuthController
import com.lightleaping.bizenglish.application.AuthService
import com.lightleaping.bizenglish.domain.UserRepository
import com.lightleaping.bizenglish.infra.InMemoryUserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class AuthControllerTest {
    private val repo: UserRepository = InMemoryUserRepository()
    private val svc = AuthService(repo)
    private val mvc: MockMvc = MockMvcBuilders.standaloneSetup(AuthController(svc)).build()
    private val om = ObjectMapper()

    @Test
    fun `signup returns 201 and token`() {
        val body = mapOf("email" to "s@t.com", "password" to "pw", "displayName" to "Sam")
        mvc.perform(
            post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(body))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.accessToken").exists())
            .andExpect(jsonPath("$.displayName").value("Sam"))
    }
}
