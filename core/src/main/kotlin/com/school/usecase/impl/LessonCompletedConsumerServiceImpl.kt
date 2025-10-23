package com.school.usecase.impl

import com.school.domain.StudentLessonProgressDomain
import com.school.port.LessonCompletedRepositoryPort
import com.school.usecase.LessonCompletedConsumerService

class LessonCompletedConsumerServiceImpl(
    private val lessonCompletedRepositoryPort: LessonCompletedRepositoryPort
) : LessonCompletedConsumerService {
    override fun updateProgress(studentLessonProgressDomain: StudentLessonProgressDomain) {
        lessonCompletedRepositoryPort.updateProgress(studentLessonProgressDomain)
    }

}