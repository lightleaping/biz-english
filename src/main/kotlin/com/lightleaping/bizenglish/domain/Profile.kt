package com.lightleaping.bizenglish.domain

data class Profile(
    val userId: Long,
    val level: Int = 1,
    val points: Int = 0,
    val bio: String? = null
)