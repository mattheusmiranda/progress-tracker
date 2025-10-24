package com.school.mapper

import com.school.avro.CourseCompletionDetailsRecord
import com.school.domain.CourseCompletionDetails
import org.springframework.stereotype.Component

@Component
class AvroMapper {
    fun toCourseCompletionDetailsAvro(courseCompletionDetails: CourseCompletionDetails) : CourseCompletionDetailsRecord {
        return CourseCompletionDetailsRecord.newBuilder()
            .setStudentLessonProgressId(courseCompletionDetails.studentLessonProgressId)
            .setTotalClasses(courseCompletionDetails.totalClasses)
            .setCompletedClasses(courseCompletionDetails.completedClasses)
            .setRemainingClasses(courseCompletionDetails.remainingClasses)
            .setPercentageCompleted(courseCompletionDetails.percentageCompleted)
            .setPercentageRemaining(courseCompletionDetails.percentageRemaining)
            .build()
    }
}