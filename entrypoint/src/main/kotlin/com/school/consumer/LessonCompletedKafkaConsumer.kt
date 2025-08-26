package com.school.consumer

import com.school.avro.StudentLessonProgressRecord
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class LessonCompletedConsumer {

    private val logger = LoggerFactory.getLogger(LessonCompletedConsumer::class.java)

    @KafkaListener(
        topics = ["lesson-completed"],
        groupId = "lesson-consumer-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consume(record: ConsumerRecord<String, StudentLessonProgressRecord>) {
        val key = record.key()
        val value = record.value()

        logger.info("Consumo do tópico 'lesson-completed'")
        logger.info("Key: $key")
        logger.info("Student ID: ${value.studentRecord.id}")
        logger.info("Lesson ID: ${value.lessonRecord.id}")
        logger.info("Completed At: ${value.completedAt}")
    }
}
