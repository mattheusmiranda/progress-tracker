package com.school.jpaRepository

import com.school.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentJpaRepository: JpaRepository<Student, Int> {
}