package com.subskribe.streaming.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
@RequestMapping("/api/mock")
class MockStreamingController {

    @GetMapping("/stream")
    fun streamMockData(): Flux<String> {
        // Emit a new chunk every 400ms
        return Flux.interval(Duration.ofMillis(400))
            .take(10) // Emit 10 chunks
            .map { "Chunk #$it\n" } // Send numbered chunks to mimic a response
    }
}