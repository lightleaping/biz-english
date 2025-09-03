package com.lightleaping.bizenglish.config

import com.lightleaping.bizenglish.domain.UserRepository
import com.lightleaping.bizenglish.infra.InMemoryUserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun userRepository(): UserRepository = InMemoryUserRepository()
}
