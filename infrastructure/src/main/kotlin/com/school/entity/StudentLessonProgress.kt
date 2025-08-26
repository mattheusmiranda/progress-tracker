package com.school.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "student_lesson_progress")
data class StudentLessonProgress(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    val student: Student,

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    val lesson: Lesson,

    @Column(name = "percentage_of_progress")
    val percentageOfProgress: Int,

    @Column(name = "completed_at")
    val completedAt: LocalDateTime? = null
)