package com.lightleaping.bizenglish.domain.scenario

import org.springframework.data.jpa.repository.JpaRepository

interface ChoiceRepository : JpaRepository<Choice, Long> {
    fun findByStageId(stageId: Long): List<Choice>
}