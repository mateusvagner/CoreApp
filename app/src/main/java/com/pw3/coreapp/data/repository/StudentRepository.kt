package com.pw3.coreapp.data.repository

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.pw3.coreapp.model.Student

interface StudentRepository {

    fun saveStudent(
        student: Student,
        onSuccessListener: OnSuccessListener<Student>,
        onFailureListener: OnFailureListener
    )
}
