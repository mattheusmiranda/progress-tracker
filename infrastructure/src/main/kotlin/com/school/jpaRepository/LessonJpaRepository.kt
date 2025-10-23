package com.school.jpaRepository

import com.school.entity.Lesson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonJpaRepository : JpaRepository<Lesson, Int> {
}