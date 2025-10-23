package com.school.usecase

import com.school.domain.StudentLessonProgressDomain

interface LessonCompletedConsumerService {
    fun updateProgress(studentLessonProgressDomain: StudentLessonProgressDomain)
}