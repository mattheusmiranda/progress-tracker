package com.school.mapper

import com.school.avro.CourseCompletionDetailsRecord
import com.school.domain.CourseCompletionDetailsDomain
import org.springframework.stereotype.Component

@Component
class AvroMapper {
    fun toCourseCompletionDetailsAvro(courseCompletionDetailsDomain: CourseCompletionDetailsDomain) : CourseCompletionDetailsRecord {
        return CourseCompletionDetailsRecord.newBuilder()
            .setStudentLessonProgressId(courseCompletionDetailsDomain.studentLessonProgressId)
            .setTotalClasses(courseCompletionDetailsDomain.totalClasses)
            .setCompletedClasses(courseCompletionDetailsDomain.completedClasses)
            .setRemainingClasses(courseCompletionDetailsDomain.remainingClasses)
            .setPercentageCompleted(courseCompletionDetailsDomain.percentageCompleted)
            .setPercentageRemaining(courseCompletionDetailsDomain.percentageRemaining)
            .build()
    }
}