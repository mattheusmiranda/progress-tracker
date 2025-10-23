package com.school.exception

class StudentFindException (
    message: String,
    cause: Throwable? = null
) : NoSuchElementException(message, cause)