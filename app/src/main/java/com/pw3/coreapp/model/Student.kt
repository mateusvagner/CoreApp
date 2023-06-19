package com.pw3.coreapp.model

data class Student(
    val name: String,
    val birthDate: String,
    val status: StudentStatus,
    val feeStatus: FeeStatus
)
