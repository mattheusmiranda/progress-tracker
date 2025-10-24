package com.school.domain

data class CourseCompletionDetails(
    val studentLessonProgressId: Int,
    val totalClasses: Int,
    val completedClasses: Int,
    val remainingClasses: Int,
    val percentageCompleted: Double,
    val percentageRemaining: Double
)