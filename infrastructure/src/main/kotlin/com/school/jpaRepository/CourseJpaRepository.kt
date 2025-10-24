package com.school.jpaRepository

import com.school.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseJpaRepository : JpaRepository<Course, Int>{
}