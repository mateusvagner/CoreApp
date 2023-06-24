package com.pw3.coreapp.model

data class Student(
    var id: String? = null,
    var name: String? = null,
    var birthDate: String? = null,
    var enrollmentDate: String? = null,
    var paymentDueDate: String? = null,
    var modality: String? = null,
    var plan: String? = null,
    var status: String? = null,
    var paymentStatus: String? = null
)
