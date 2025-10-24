package com.school.port

import com.school.domain.CourseCompletionDetails
import com.school.domain.StudentLessonProgressDomain

interface LessonCompletedRepositoryPort {
    fun updateProgress(studentLessonProgressDomain: StudentLessonProgressDomain)
    fun calculateProgress(studentLessonProgressDomain: StudentLessonProgressDomain): CourseCompletionDetails
}