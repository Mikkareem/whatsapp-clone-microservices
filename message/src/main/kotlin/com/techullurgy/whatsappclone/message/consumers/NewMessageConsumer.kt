package com.techullurgy.whatsappclone.message.consumers

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class NewMessageConsumer(
    private val deliveryKafkaTemplate: KafkaTemplate<String, String>
) {
    @KafkaListener(topics = ["NEW_MESSAGE"], groupId = "msg-service")
    fun newMessageConsumer(msg: String) {
        println("Message received for Message Service as [$msg]")
        val message = Json.decodeFromString<Message>(msg)

        val fMsg = Json.encodeToString(message)
        deliveryKafkaTemplate.send("DELIVERABLE_MESSAGE", fMsg)
    }
}

@Serializable
data class Message(
    val sender: String,
    val receiver: String,
    val message: String
)