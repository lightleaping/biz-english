package com.lightleaping.bizenglish.interfaces.scenario

import com.lightleaping.bizenglish.application.scenario.ScenarioService
import com.lightleaping.bizenglish.application.scenario.dto.ScenarioDetailDto
import com.lightleaping.bizenglish.application.scenario.dto.ScenarioSummaryDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/scenario")
class ScenarioController(
    private val scenarioService: ScenarioService
) {
    @GetMapping("/list")
    fun list(): ResponseEntity<List<ScenarioSummaryDto>> =
        ResponseEntity.ok(scenarioService.list())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<ScenarioDetailDto> =
        ResponseEntity.ok(scenarioService.getById(id))
}
