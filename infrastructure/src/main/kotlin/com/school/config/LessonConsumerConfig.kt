package com.school.config

import com.school.port.LessonCompletedRepositoryPort
import com.school.usecase.LessonCompletedConsumerService
import com.school.usecase.impl.LessonCompletedConsumerServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LessonConsumerConfig(
    private val lessonCompletedRepositoryPort: LessonCompletedRepositoryPort
) {

    @Bean
    fun lessonCompletedConsumerService(): LessonCompletedConsumerService {
        return LessonCompletedConsumerServiceImpl(lessonCompletedRepositoryPort)
    }
}
