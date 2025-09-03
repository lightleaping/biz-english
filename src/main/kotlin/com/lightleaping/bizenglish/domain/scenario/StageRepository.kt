package com.lightleaping.bizenglish.domain.scenario

import org.springframework.data.jpa.repository.JpaRepository

interface StageRepository : JpaRepository<Stage, Long> {
    fun findByScenarioIdOrderByOrderNoAsc(scenarioId: Long): List<Stage>
    fun countByScenarioId(scenarioId: Long): Long
}