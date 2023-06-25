package com.pw3.coreapp.ui.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.pw3.coreapp.model.Student
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
fun List<Student>.filterStudentsBirthDayWithinNext7Days(): List<Student> {
    return this.filter { student ->
        student.daysUntilBirthday() <= 7
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Student.daysUntilBirthday(): Int {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val today = LocalDate.now()

    val currentYear = today.year
    val birthdayDate = LocalDate.parse("${this.birthDate?.substring(0,5)}/${currentYear}", dateFormatter)

    val nextBirthday = if (today.isAfter(birthdayDate)) {
        birthdayDate.plusYears(1)
    } else {
        birthdayDate
    }

    return ChronoUnit.DAYS.between(today, nextBirthday).toInt()
}

@RequiresApi(Build.VERSION_CODES.O)
fun List<Student>.filterStudentsPaymentDayWithinNext5Days(): List<Student> {
    val today = LocalDate.now().dayOfMonth

    return this.filter { student ->
        val daysDifference = student.paymentDueDate?.toDouble()?.minus(today)
        daysDifference!! <= 5 &&  daysDifference > 0
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun List<Student>.filterStudentsPaymentDayPending(): List<Student> {
    val today = LocalDate.now().dayOfMonth

    return this.filter { student ->
        val daysDifference = student.paymentDueDate?.toDouble()?.minus(today)
        daysDifference!! < 0
    }
}
