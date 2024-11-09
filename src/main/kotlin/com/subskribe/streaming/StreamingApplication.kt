package com.subskribe.streaming

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class StreamingApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<StreamingApplication>(*args)
        }
    }

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder().build()
    }
}