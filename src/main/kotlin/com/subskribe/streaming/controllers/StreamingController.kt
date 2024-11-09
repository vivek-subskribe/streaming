package com.subskribe.streaming.controllers

import com.subskribe.streaming.services.StreamingService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class StreamingController(val streamingService: StreamingService) {

    @GetMapping("/stream")
    suspend fun streamData(): ResponseEntity<*> {
        try {
            val flux = streamingService.streamResponse()
            return ResponseEntity.ok().contentType(MediaType.valueOf("text/event-stream")).body(flux)
        } catch (e: Exception) {
            return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON).body(
                """
                {"error": "An error occurred while streaming the data. ${e.message}"}
            """.trimIndent()
            )
        }
    }

}