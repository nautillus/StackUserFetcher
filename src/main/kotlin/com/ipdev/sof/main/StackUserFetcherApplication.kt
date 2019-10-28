package com.ipdev.sof.main

import com.ipdev.sof.main.service.ApiConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ApiConfig::class)
class StackUserFetcherApplication

fun main(args: Array<String>) {
	runApplication<StackUserFetcherApplication>(*args)
}
