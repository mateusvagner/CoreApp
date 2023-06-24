package com.pw3.coreapp.model

data class StudentPlan(
    val all: List<String> = listOf(
        "Mensal",
        "Trimestral",
        "Semestral",
        "Anual"
    )
)
