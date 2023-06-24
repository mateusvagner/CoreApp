package com.pw3.coreapp.model

import androidx.annotation.StringRes
import com.pw3.CoreApp.R

//enum class StudentStatus(@StringRes val stringRes: Int) {
//    active(R.string.student_status_active),
//    inactive(R.string.student_status_inactive),
//    onPause(R.string.student_status_on_pause);
//}

data class StudentStatus(
    val all: List<String> = listOf(
        "Ativo",
        "Inativo",
        "Em Pausa"
    )
)
