package com.school.adapter

import com.school.domain.CourseCompletionDetails
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

    override fun calculateProgress(studentLessonProgressDomain: StudentLessonProgressDomain): CourseCompletionDetails {
        val course = courseJpaRepository.findById(studentLessonProgressDomain.lesson.courseId)
            .orElseThrow { CourseFindException("Curso com ID ${studentLessonProgressDomain.lesson.courseId} não encontrado.") }

        val totalClasses: Int = course.totalNumberOfClasses

        val completedClasses: Int = studentLessonProgressDomain.percentageOfProgress

        val percentageCompleted = (completedClasses.toDouble() / totalClasses) * 100
        val remainingClasses = totalClasses - completedClasses
        val percentageRemaining = 100.0 - percentageCompleted

        return CourseCompletionDetails(
            studentLessonProgressId = studentLessonProgressDomain.id,
            totalClasses = totalClasses,
            completedClasses = completedClasses,
            remainingClasses = remainingClasses,
            percentageCompleted = percentageCompleted,
            percentageRemaining = percentageRemaining
        )
    }

}