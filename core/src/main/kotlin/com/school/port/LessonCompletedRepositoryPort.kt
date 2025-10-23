package com.school.port

import com.school.domain.StudentLessonProgressDomain

interface LessonCompletedRepositoryPort {
    fun updateProgress(studentLessonProgressDomain: StudentLessonProgressDomain)
}