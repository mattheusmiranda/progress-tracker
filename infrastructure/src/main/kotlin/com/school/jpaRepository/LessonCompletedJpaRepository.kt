package com.school.jpaRepository

import com.school.entity.StudentLessonProgress
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

@Repository
interface LessonCompletedJpaRepository : JpaRepository<StudentLessonProgress, Int> {
    fun findByStudentId(id: Int): Optional<StudentLessonProgress>
    fun findByLessonId(id: Int): Optional<StudentLessonProgress>
}