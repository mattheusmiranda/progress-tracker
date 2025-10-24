package com.school.producer

import com.school.avro.CourseCompletionDetailsRecord
import com.school.config.KafkaTopicsConfig
import com.school.domain.CourseCompletionDetails
import com.school.mapper.AvroMapper
import com.school.port.output.StudentProgressUpdatedEventPublisher
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CourseCompletionDetailsKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, CourseCompletionDetailsRecord>,
    private val avroMapper: AvroMapper,
    private val kafkaTopicsConfig: KafkaTopicsConfig
): StudentProgressUpdatedEventPublisher {

    private val logger = LoggerFactory.getLogger(javaClass)

    private val topic = kafkaTopicsConfig.studentProgressUpdated

    override fun publish(courseCompletionDetails: CourseCompletionDetails) {
        try {
            val avroRecord = avroMapper.toCourseCompletionDetailsAvro(courseCompletionDetails)
            kafkaTemplate.send(topic, courseCompletionDetails.studentLessonProgressId.toString(), avroRecord)

            logger.info(
                "message=\"Course completion event successfully published\" " +
                        "topic=\"$topic\" " +
                        "totalClasses=${courseCompletionDetails.totalClasses} " +
                        "completedClasses=${courseCompletionDetails.completedClasses} " +
                        "percentageCompleted=${courseCompletionDetails.percentageCompleted}"
            )
        } catch (e: Exception) {
            logger.error(
                "message=\"Failed to publish course completion event\" " +
                        "topic=\"$topic\" " +
                        "completedClasses=${courseCompletionDetails.completedClasses} " +
                        "percentageCompleted=${courseCompletionDetails.percentageCompleted}",
                e
            )
        } finally {
            MDC.clear()
        }
    }

}