package com.pw3.coreapp.data.repository

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase
import com.pw3.coreapp.model.Student


class FirebaseStudentRepository: StudentRepository {

    var studentDatabase = FirebaseDatabase.getInstance().reference.child("student") // TODO inject

    override fun saveStudent(student: Student, onSuccessListener: OnSuccessListener<Student>, onFailureListener: OnFailureListener) {
        TODO("Not yet implemented")
    }
}
