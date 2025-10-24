package com.school.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KafkaTopicsConfig(
    @Value("\${topics.student-progress-updated}")
    val studentProgressUpdated: String
)