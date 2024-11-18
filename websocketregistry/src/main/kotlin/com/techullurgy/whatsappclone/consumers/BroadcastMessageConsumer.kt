package com.techullurgy.whatsappclone.consumers

import com.techullurgy.whatsappclone.connectedUsers
import com.techullurgy.whatsappclone.kafka.consumeMessage
import com.techullurgy.whatsappclone.model.Message
import io.ktor.websocket.Frame
import kotlinx.serialization.json.Json

suspend fun consumeBroadcastMessageTopic() {
    consumeMessage(listOf("BROADCAST_MESSAGE")) { msg ->
        println("Message received to Websocket Registry as [$msg]")

        val message = Json.decodeFromString<Message>(msg)
        connectedUsers
            .filter { it.key == message.receiver }
            .forEach {
                it.value.outgoing.send(Frame.Text(msg))
            }
    }
}