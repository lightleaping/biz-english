package com.lightleaping.bizenglish.application.scenario

import com.lightleaping.bizenglish.application.scenario.dto.*
import com.lightleaping.bizenglish.domain.scenario.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ScenarioService(
    private val scenarioRepo: ScenarioRepository,
    private val stageRepo: StageRepository,
    private val choiceRepo: ChoiceRepository
) {

    @Transactional(readOnly = true)
    fun list(): List<ScenarioSummaryDto> {
        val scenarios = scenarioRepo.findAll()
        return scenarios.map { s ->
            val count = stageRepo.countByScenarioId(s.id)
            ScenarioSummaryDto(
                id = s.id, code = s.code, title = s.title, stageCount = count
            )
        }
    }

    @Transactional(readOnly = true)
    fun getById(id: Long): ScenarioDetailDto {
        val scenario = scenarioRepo.findById(id)
            .orElseThrow { NoSuchElementException("Scenario not found: $id") }

        val stages = stageRepo.findByScenarioIdOrderByOrderNoAsc(scenario.id)
        val stageDtos = stages.map { st ->
            val choices = choiceRepo.findByStageId(st.id).map { ch ->
                ChoiceDto(
                    id = ch.id, text = ch.text, nextStageId = ch.nextStageId,
                    scoreDelta = ch.scoreDelta, hint = ch.hint
                )
            }
            StageDto(
                id = st.id,
                name = st.name,
                content = st.content,
                orderNo = st.orderNo,
                isTerminal = st.isTerminal,
                audioUrl = st.audioUrl,
                choices = choices
            )
        }

        return ScenarioDetailDto(
            id = scenario.id,
            code = scenario.code,
            title = scenario.title,
            description = scenario.description,
            firstStageId = scenario.firstStageId,
            stages = stageDtos
        )
    }
}
