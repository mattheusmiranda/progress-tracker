package com.school.mapper

import com.school.domain.StudentLessonProgressDomain
import com.school.entity.StudentLessonProgress
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper(uses = [LessonMapper::class])
interface LessonCompletedConsumerMapper {

    companion object {
        val INSTANCE: LessonCompletedConsumerMapper =
            Mappers.getMapper(LessonCompletedConsumerMapper::class.java)
    }

    fun toEntity(domain: StudentLessonProgressDomain): StudentLessonProgress
}
