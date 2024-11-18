package com.techullurgy.whatsappclone

import com.techullurgy.whatsappclone.consumers.consumeBroadcastMessageTopic
import io.ktor.server.application.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Application.configureKafka() {
    CoroutineScope(Dispatchers.IO).launch {
        consumeBroadcastMessageTopic()
    }
}