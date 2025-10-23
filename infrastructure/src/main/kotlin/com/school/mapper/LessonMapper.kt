package com.school.mapper

import com.school.domain.LessonDomain
import com.school.entity.Lesson
import org.mapstruct.Mapper

@Mapper
interface LessonMapper {
    fun toEntity(domain: LessonDomain): Lesson
}
