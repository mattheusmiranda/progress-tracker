package com.school.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "title")
    val title: String,

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "total_number_of_classes")
    val totalNumberOfClasses: Int = 0,

    @Column(name = "created_at")
    val createdAt: LocalDate = LocalDate.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDate = LocalDate.now()
)