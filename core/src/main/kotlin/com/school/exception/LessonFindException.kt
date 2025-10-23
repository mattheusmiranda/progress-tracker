package com.school.exception

class LessonFindException (
    message: String,
    cause: Throwable? = null
) : NoSuchElementException(message, cause)