package com.school.domain

import java.time.LocalDate

data class LessonDomain(
    val id: Int = 0,
    val courseId: Int,
    val title: String,
    val durationSeconds: Int,
    val createdAt: LocalDate = LocalDate.now(),
    val updatedAt: LocalDate = LocalDate.now()
)
