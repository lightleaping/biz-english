package com.lightleaping.bizenglish.domain.scenario

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "stage",
    indexes = [Index(name = "idx_stage_scenario", columnList = "scenario_id")])
class Stage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "scenario_id", nullable = false)
    val scenarioId: Long,

    @Column(nullable = false, length = 150)
    val name: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    val content: String,

    @Column(name = "order_no", nullable = false)
    val orderNo: Int,

    @Column(name = "is_terminal", nullable = false)
    val isTerminal: Boolean = false,

    @Column(name = "audio_url", length = 500)
    val audioUrl: String? = null,

    @Column(name = "created_at")
    val createdAt: Instant? = null,

    @Column(name = "updated_at")
    val updatedAt: Instant? = null,
)
