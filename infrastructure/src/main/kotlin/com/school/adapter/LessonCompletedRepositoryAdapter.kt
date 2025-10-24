package com.school.adapter

import com.school.domain.CourseCompletionDetailsDomain
import com.school.domain.StudentLessonProgressDomain
import com.school.exception.CourseFindException
import com.school.exception.LessonFindException
import com.school.exception.StudentFindException
import com.school.jpaRepository.CourseJpaRepository
import com.school.jpaRepository.LessonCompletedJpaRepository
import com.school.jpaRepository.LessonJpaRepository
import com.school.mapper.AvroMapper
import com.school.mapper.LessonCompletedConsumerMapper
import com.school.port.LessonCompletedRepositoryPort
import com.school.producer.CourseCompletionDetailsKafkaProducer
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class LessonCompletedRepositoryAdapter(
    private val lessonCompletedJpaRepository: LessonCompletedJpaRepository,
    private val lessonJpaRepository: LessonJpaRepository,
    private val studentJpaRepository: LessonJpaRepository,
    private val courseJpaRepository: CourseJpaRepository,
    private val avroMapper: AvroMapper,
    private val courseCompletionDetailsKafkaProducer: CourseCompletionDetailsKafkaProducer
): LessonCompletedRepositoryPort {

    private val logger = LoggerFactory.getLogger(LessonCompletedRepositoryAdapter::class.java)

    override fun updateProgress(studentLessonProgressDomain: StudentLessonProgressDomain) {
        lessonJpaRepository.findById(studentLessonProgressDomain.lesson.id).orElseThrow {
            LessonFindException("Lesson with ID ${studentLessonProgressDomain.lesson.id} not found") }
        studentJpaRepository.findById(studentLessonProgressDomain.student.id).orElseThrow {
            StudentFindException("Student with ID ${studentLessonProgressDomain.student.id} not found") }

        val entity = LessonCompletedConsumerMapper.INSTANCE.toEntity(studentLessonProgressDomain)
        entity.completedAt = LocalDateTime.now()

        lessonCompletedJpaRepository.save(entity)
        val courseCompletionDetails = calculateProgress(studentLessonProgressDomain)
        courseCompletionDetailsKafkaProducer.publish(courseCompletionDetails)
    }

    override fun calculateProgress(studentLessonProgressDomain: StudentLessonProgressDomain): CourseCompletionDetailsDomain {
        try {
            val course = courseJpaRepository.findById(studentLessonProgressDomain.lesson.courseId)
                .orElseThrow { CourseFindException("Course with ID ${studentLessonProgressDomain.lesson.courseId} not found") }

            val totalClasses: Int = course.totalNumberOfClasses
            val completedClasses: Int = studentLessonProgressDomain.percentageOfProgress
            val percentageCompleted = (completedClasses.toDouble() / totalClasses) * 100
            val remainingClasses = totalClasses - completedClasses
            val percentageRemaining = 100.0 - percentageCompleted

            MDC.put("studentLessonProgressId", studentLessonProgressDomain.id.toString())
            MDC.put("totalClasses", totalClasses.toString())
            MDC.put("completedClasses", completedClasses.toString())
            MDC.put("remainingClasses", remainingClasses.toString())
            MDC.put("percentageCompleted", String.format("%.2f", percentageCompleted))
            MDC.put("percentageRemaining", String.format("%.2f", percentageRemaining))

            logger.info("Calculated course completion details")

            return CourseCompletionDetailsDomain(
                studentLessonProgressId = studentLessonProgressDomain.id,
                totalClasses = totalClasses,
                completedClasses = completedClasses,
                remainingClasses = remainingClasses,
                percentageCompleted = percentageCompleted,
                percentageRemaining = percentageRemaining
            )
        } finally {
            MDC.clear()
        }
    }


}