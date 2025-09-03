package com.lightleaping.bizenglish.application.scenario.dto

data class ScenarioSummaryDto(
    val id: Long,
    val code: String,
    val title: String,
    val stageCount: Long
)

data class ChoiceDto(
    val id: Long,
    val text: String,
    val nextStageId: Long?,
    val scoreDelta: Int,
    val hint: String?
)

data class StageDto(
    val id: Long,
    val name: String,
    val content: String,
    val orderNo: Int,
    val isTerminal: Boolean,
    val audioUrl: String?,
    val choices: List<ChoiceDto>
)

data class ScenarioDetailDto(
    val id: Long,
    val code: String,
    val title: String,
    val description: String?,
    val firstStageId: Long?,
    val stages: List<StageDto>
)
