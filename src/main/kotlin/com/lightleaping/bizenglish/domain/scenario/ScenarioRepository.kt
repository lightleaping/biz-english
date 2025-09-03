package com.lightleaping.bizenglish.domain.scenario

import org.springframework.data.jpa.repository.JpaRepository

interface ScenarioRepository : JpaRepository<Scenario, Long> {
    fun findByCode(code: String): Scenario?
}