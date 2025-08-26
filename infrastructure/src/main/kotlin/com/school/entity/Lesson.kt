package com.school.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "lessons")
data class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "course_id")
    val courseId: Int,

    @Column(name = "title")
    val title: String,

    @Column(name = "duration_seconds")
    val durationSeconds: Int,

    @Column(name = "created_at")
    val createdAt: LocalDate = LocalDate.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDate = LocalDate.now()
)