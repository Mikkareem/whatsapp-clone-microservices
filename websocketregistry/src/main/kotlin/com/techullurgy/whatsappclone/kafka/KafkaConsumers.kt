package com.techullurgy.whatsappclone.kafka

import kotlinx.coroutines.delay
import java.time.Duration

suspend inline fun consumeMessage(
    topics: List<String>,
    block: (String) -> Unit
) {
    val consumer = KafkaCreator.consumer

    consumer.subscribe(topics)

    while (true) {
        val consumerRecords = consumer.poll(Duration.ofSeconds(1))

        consumerRecords.forEach {
            block(it.value())
        }

        delay(5000)
    }
}