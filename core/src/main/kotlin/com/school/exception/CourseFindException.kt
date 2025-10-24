package com.school.exception

class CourseFindException (
    message: String,
    cause: Throwable? = null
) : NoSuchElementException(message, cause)