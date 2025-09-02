package com.lightleaping.biz_english

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BizEnglishApplication

fun main(args: Array<String>) {
	runApplication<BizEnglishApplication>(*args)
}

@RestController
class HealthController {
	@GetMapping("/health")
	fun health() = mapOf("status" to "OK")
}
