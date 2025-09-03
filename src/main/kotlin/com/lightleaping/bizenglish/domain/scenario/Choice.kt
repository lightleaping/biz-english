package com.lightleaping.bizenglish.domain.scenario

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "choice",
    indexes = [Index(name = "idx_choice_stage", columnList = "stage_id")])
class Choice(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "stage_id", nullable = false)
    val stageId: Long,

    @Column(nullable = false, length = 500)
    val text: String,

    @Column(name = "next_stage_id")
    val nextStageId: Long? = null,

    @Column(name = "score_delta", nullable = false)
    val scoreDelta: Int = 0,

    @Column(length = 500)
    val hint: String? = null,

    @Column(name = "created_at")
    val createdAt: Instant? = null,

    @Column(name = "updated_at")
    val updatedAt: Instant? = null,
)
