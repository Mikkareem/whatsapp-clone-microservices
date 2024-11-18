package com.techullurgy.whatsappclone.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val sender: String,
    val receiver: String,
    val message: String
)