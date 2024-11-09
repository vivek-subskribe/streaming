package com.subskribe.streaming.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.util.concurrent.atomic.AtomicInteger

@Service
class StreamingService(
    private val webClient: WebClient
) {

    val count: AtomicInteger = AtomicInteger(0)

    suspend fun streamResponse(): Flux<String> = withContext(Dispatchers.IO) {

        count.incrementAndGet()

        if (count.get() % 2 == 0) throw RuntimeException("Test exception")

        // Perform the WebClient call in the IO dispatcher to avoid blocking the event loop
        val fluxResponse = webClient.get()
            .uri("http://localhost:9090/api/mock/stream") // Mock endpoint
            .retrieve()
            .bodyToFlux(String::class.java) // Get the Flux of the response body

        return@withContext fluxResponse
    }
}
