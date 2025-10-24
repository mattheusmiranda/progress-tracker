package com.school.port.output

import com.school.domain.CourseCompletionDetails

interface StudentProgressUpdatedEventPublisher {
    fun publish(courseCompletionDetails: CourseCompletionDetails)
}