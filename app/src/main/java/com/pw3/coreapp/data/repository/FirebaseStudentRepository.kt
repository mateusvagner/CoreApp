package com.pw3.coreapp.data.repository

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.pw3.coreapp.data.DatabaseTables
import com.pw3.coreapp.model.Student

//class FirebaseStudentRepository @Inject constructor(
//    @StudentsDatabaseReference private val studentsDatabaseReference: DatabaseReference
//): StudentRepository {

class FirebaseStudentRepository : StudentRepository {

    private val studentsDatabaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child(
            DatabaseTables.STUDENTS
        )

    override fun saveStudent(
        student: Student,
        onSuccessListener: OnSuccessListener<Student>,
        onFailureListener: OnFailureListener
    ) {
        TODO("Not yet implemented")
    }
}
