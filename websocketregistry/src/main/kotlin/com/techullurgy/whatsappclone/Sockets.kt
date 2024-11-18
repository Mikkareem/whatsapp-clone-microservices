package com.techullurgy.whatsappclone

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration.Companion.seconds

val connectedUsers = ConcurrentHashMap<String, WebSocketServerSession>()

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        webSocket("/ws/{name}") { // websocketSession
            val name = call.parameters["name"] ?: ""

            if(name.trim() == "") {
                close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "No Name Provided"))
            } else {
                try {
                    connectedUsers.put(name, this)

                    for (i in incoming) {
                        Unit
                    }
                } finally {
                    connectedUsers.remove(name)
                }
            }
        }
    }
}
