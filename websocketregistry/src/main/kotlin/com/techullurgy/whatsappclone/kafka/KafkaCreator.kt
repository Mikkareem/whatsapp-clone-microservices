package com.techullurgy.whatsappclone.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import java.util.Properties

object KafkaCreator {
    val producer by lazy {
        val properties = Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
            put(ProducerConfig.CLIENT_ID_CONFIG, "client-1")
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
        }
        KafkaProducer<String, String>(properties)
    }

    val consumer by lazy {
        val props = Properties().apply {
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
            put(ConsumerConfig.GROUP_ID_CONFIG, "ws-registry")
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
            put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 30000)
        }

        KafkaConsumer<String, String>(props)
    }
}