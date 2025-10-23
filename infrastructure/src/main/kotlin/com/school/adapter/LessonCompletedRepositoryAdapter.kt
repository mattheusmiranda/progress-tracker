package com.school.adapter

import com.school.domain.StudentLessonProgressDomain
import com.school.exception.LessonFindException
import com.school.exception.StudentFindException
import com.school.jpaRepository.LessonCompletedJpaRepository
import com.school.jpaRepository.LessonJpaRepository
import com.school.mapper.LessonCompletedConsumerMapper
import com.school.port.LessonCompletedRepositoryPort
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class LessonCompletedRepositoryAdapter(
    private val lessonCompletedJpaRepository: LessonCompletedJpaRepository,
    private val lessonJpaRepository: LessonJpaRepository,
    private val studentJpaRepository: LessonJpaRepository
): LessonCompletedRepositoryPort {
    override fun updateProgress(studentLessonProgressDomain: StudentLessonProgressDomain) {
        lessonJpaRepository.findById(studentLessonProgressDomain.lesson.id).orElseThrow {
            LessonFindException("Lesson with ID ${studentLessonProgressDomain.lesson.id} not found") }
        studentJpaRepository.findById(studentLessonProgressDomain.student.id).orElseThrow {
            StudentFindException("Student with ID ${studentLessonProgressDomain.student.id} not found") }

        val entity = LessonCompletedConsumerMapper.INSTANCE.toEntity(studentLessonProgressDomain)
        entity.completedAt = LocalDateTime.now()

        lessonCompletedJpaRepository.save(entity)
    }
}