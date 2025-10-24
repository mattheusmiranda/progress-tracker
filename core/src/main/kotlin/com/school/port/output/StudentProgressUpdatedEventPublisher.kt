package com.school.port.output

import com.school.domain.CourseCompletionDetailsDomain

interface StudentProgressUpdatedEventPublisher {
    fun publish(courseCompletionDetailsDomain: CourseCompletionDetailsDomain)
}