package com.lightleaping.bizenglish.domain.scenario

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "scenario")
class Scenario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 64)
    val code: String,

    @Column(nullable = false, length = 200)
    val title: String,

    @Column(columnDefinition = "TEXT")
    val description: String? = null,

    @Column(name = "first_stage_id")
    val firstStageId: Long? = null,

    @Column(name = "created_at")
    val createdAt: Instant? = null,

    @Column(name = "updated_at")
    val updatedAt: Instant? = null,
)
