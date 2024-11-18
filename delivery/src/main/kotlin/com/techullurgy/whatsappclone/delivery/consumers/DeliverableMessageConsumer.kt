package com.techullurgy.whatsappclone.delivery.consumers

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class DeliverableMessageConsumer(
    private val websocketRegistryTemplate: KafkaTemplate<String, String>
) {
    @KafkaListener(topics = ["DELIVERABLE_MESSAGE"], groupId = "dlv-service")
    fun deliverableMessageConsumer(msg: String) {
        val message = Json.decodeFromString<Message>(msg)
        println("Message received for Delivery Service as [$message]")

        val fMsg = Json.encodeToString(message)
        websocketRegistryTemplate.send("BROADCAST_MESSAGE", fMsg)
    }
}

@Serializable
data class Message(
    val sender: String,
    val receiver: String,
    val message: String
)