package com.pw3.coreapp.model

data class Student(
    val name: String,
    val birthDate: String,
    val enrollmentDate: String,
    val paymentDueDate: String,
    val modality: String,
    val plan: String,
    val status: StudentStatus,
    val paymentStatus: PaymentStatus
)
