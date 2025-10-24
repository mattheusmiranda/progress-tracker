package com.school.producer

import com.school.avro.CourseCompletionDetailsRecord
import com.school.config.KafkaTopicsConfig
import com.school.domain.CourseCompletionDetailsDomain
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

    override fun publish(courseCompletionDetailsDomain: CourseCompletionDetailsDomain) {
        try {
            toLog(courseCompletionDetailsDomain)
            val avroRecord = avroMapper.toCourseCompletionDetailsAvro(courseCompletionDetailsDomain)
            kafkaTemplate.send(topic, courseCompletionDetailsDomain.studentLessonProgressId.toString(), avroRecord)

            logger.info("Course completion event successfully published")
        } catch (e: Exception) {
            logger.error("Failed to publish course completion event", e)
        } finally {
            MDC.clear()
        }
    }

    fun toLog(courseCompletionDetailsDomain: CourseCompletionDetailsDomain) {
        MDC.put("topic", topic)
        MDC.put("studentLessonProgressId", courseCompletionDetailsDomain.studentLessonProgressId.toString())
        MDC.put("totalClasses", courseCompletionDetailsDomain.totalClasses.toString())
        MDC.put("completedClasses", courseCompletionDetailsDomain.completedClasses.toString())
        MDC.put("percentageCompleted", courseCompletionDetailsDomain.percentageCompleted.toString())
    }


}