package com.ipdev.sof.main.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Base configs for application
 */
@Configuration
@ConfigurationProperties
class ApiConfig {
    @Value("\${sof.api.baseUrl}")
    var baseUrl: String = "https://api.stackexchange.com/"
    @Value("\${sof.api.version}")
    var version: String = "2.2"
    @Value("\${sof.api.defUrlSep}")
    var defUrlSep: String = "/"
}
