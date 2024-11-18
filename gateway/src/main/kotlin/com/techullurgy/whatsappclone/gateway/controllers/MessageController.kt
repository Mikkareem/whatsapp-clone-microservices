package com.techullurgy.whatsappclone.gateway.controllers

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val newMessageKafkaTemplate: KafkaTemplate<String, String>
) {

    @PostMapping("/message")
    fun newMessage(@RequestBody body: Message) {
        val messageEvent = Json.encodeToString(body)
        newMessageKafkaTemplate.send("NEW_MESSAGE", messageEvent)
    }
}

@Serializable
data class Message(
    val sender: String,
    val receiver: String,
    val message: String
)