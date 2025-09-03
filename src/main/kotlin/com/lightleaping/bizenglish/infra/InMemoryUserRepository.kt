package com.lightleaping.bizenglish.infra

import com.lightleaping.bizenglish.domain.User
import com.lightleaping.bizenglish.domain.UserRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Repository
class InMemoryUserRepository : UserRepository {
    private val seq = AtomicLong(0L)
    private val byId = ConcurrentHashMap<Long, User>()
    private val byEmail = ConcurrentHashMap<String, Long>()

    override fun findByEmail(email: String): User? =
        byEmail[email]?.let { byId[it] }

    override fun findById(id: Long): User? = byId[id]

    override fun save(user: User): User {
        val id = if (user.id == 0L) seq.incrementAndGet() else user.id
        val newUser = user.copy(id = id)
        byId[id] = newUser
        byEmail[newUser.email] = id
        return newUser
    }
}