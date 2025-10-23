package com.school.mapper

import com.school.avro.LessonRecord
import com.school.avro.StudentLessonProgressRecord
import com.school.avro.StudentRecord
import com.school.domain.LessonDomain
import com.school.domain.StudentDomain
import com.school.domain.StudentLessonProgressDomain
import org.springframework.stereotype.Component

@Component
class LessonCompletedConsumerManualMapper {

    fun toStudentLessonProgressDomain(record: StudentLessonProgressRecord): StudentLessonProgressDomain {
        return StudentLessonProgressDomain(
            id = record.id,
            student = toStudentDomain(record.studentRecord),
            lesson = toLessonDomain(record.lessonRecord),
            percentageOfProgress = record.percentageOfProgress,
            completedAt = record.completedAt
        )
    }

    fun toLessonDomain(lessonRecord: LessonRecord): LessonDomain {
        return LessonDomain(
            id = lessonRecord.id,
            courseId = lessonRecord.courseId,
            title = lessonRecord.title,
            durationSeconds = lessonRecord.durationSeconds,
            createdAt = lessonRecord.createdAt,
            updatedAt = lessonRecord.updatedAt
        )
    }

    fun toStudentDomain(studentRecord: StudentRecord): StudentDomain {
        return StudentDomain(
            id = studentRecord.id,
            name = studentRecord.name,
            email = studentRecord.email,
            createdAt = studentRecord.createdAt,
            updatedAt = studentRecord.updatedAt
        )
    }
}
