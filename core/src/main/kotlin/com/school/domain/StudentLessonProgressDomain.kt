package com.school.domain

import java.time.LocalDate

data class StudentLessonProgressDomain(
    val id: Int,
    val student: StudentDomain,
    val lesson: LessonDomain,
    val percentageOfProgress: Int,
    val completedAt: LocalDate? = null
)
