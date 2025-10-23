package com.school.consumer

import com.school.avro.StudentLessonProgressRecord
import com.school.mapper.LessonCompletedConsumerManualMapper
import com.school.usecase.LessonCompletedConsumerService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class LessonCompletedConsumer(
    private val lessonCompletedConsumerService: LessonCompletedConsumerService,
    private val lessonCompletedConsumerManualMapper: LessonCompletedConsumerManualMapper
) {

    private val logger = LoggerFactory.getLogger(LessonCompletedConsumer::class.java)

    @KafkaListener(
        topics = ["lesson_completed"],
        groupId = "lesson-consumer-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun consume(record: ConsumerRecord<String, StudentLessonProgressRecord>) {
        val studentLessonProgressDomain = lessonCompletedConsumerManualMapper.toStudentLessonProgressDomain(record.value())
        lessonCompletedConsumerService.updateProgress(studentLessonProgressDomain)
        toLog(record)
    }


    fun toLog(record: ConsumerRecord<String, StudentLessonProgressRecord>) {
        logger.info("Consumo do tópico 'lesson_completed'")
        logger.info("Key: ${record.key()}")
        logger.info("Student ID: ${record.value().studentRecord.id}")
        logger.info("Lesson ID: ${record.value().lessonRecord.id}")
        logger.info("Completed At: ${record.value().completedAt}")
    }

}
