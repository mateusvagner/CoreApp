package com.pw3.coreapp.model

import androidx.annotation.StringRes
import com.pw3.CoreApp.R

//enum class PaymentStatus(@StringRes val stringRes: Int) {
//    paid(R.string.student_payment_status_paid),
//    pending(R.string.student_payment_status_pending);
//}

data class PaymentStatus(
    val all: List<String> = listOf(
        "Pago",
        "Pendente"
    )
)
