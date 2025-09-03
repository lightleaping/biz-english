package com.lightleaping.bizenglish.application.scenario

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class ScenarioServiceTest(
    @Autowired private val scenarioService: ScenarioService
) {
    @Test
    fun `list returns seeded scenario`() {
        val list = scenarioService.list()
        assertTrue(list.any { it.code == "PRJ_DELAY_REPORT_V1" })
    }
}
