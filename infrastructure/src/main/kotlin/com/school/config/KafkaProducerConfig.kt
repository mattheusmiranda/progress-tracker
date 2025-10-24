package com.school.config

import com.school.avro.CourseCompletionDetailsRecord
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import java.util.HashMap

@Configuration
class KafkaProducerConfig {

    private fun createConfigProps(): MutableMap<String, Any> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "io.confluent.kafka.serializers.KafkaAvroSerializer"
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "io.confluent.kafka.serializers.KafkaAvroSerializer"
        configProps["schema.registry.url"] = "http://localhost:8081"
        configProps[ProducerConfig.ACKS_CONFIG] = "all"
        configProps[ProducerConfig.RETRIES_CONFIG] = 3

        return configProps
    }

    @Bean
    fun studentLessonProgressKafkaTemplate(): KafkaTemplate<String, CourseCompletionDetailsRecord> {
        val producerFactory = DefaultKafkaProducerFactory<String, CourseCompletionDetailsRecord>(createConfigProps())
        return KafkaTemplate(producerFactory)
    }

}