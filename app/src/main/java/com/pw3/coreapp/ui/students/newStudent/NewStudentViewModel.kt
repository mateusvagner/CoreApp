package com.pw3.coreapp.ui.students.newStudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.pw3.coreapp.data.DatabaseTables
import com.pw3.coreapp.model.Student
import com.pw3.coreapp.ui.util.SingleLiveEventLiveData

class NewStudentViewModel : ViewModel() {

    private val studentsDatabaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child(
            DatabaseTables.STUDENTS
        )

    private val _isSaveStudentSucceeded: MutableLiveData<Boolean> = SingleLiveEventLiveData()
    val isSaveStudentSucceeded: LiveData<Boolean> = _isSaveStudentSucceeded

    private val _isSaveStudentFailed: MutableLiveData<String> = SingleLiveEventLiveData()
    val isSaveStudentFailed: LiveData<String> = _isSaveStudentFailed

    fun saveStudent(student: Student) {
        studentsDatabaseReference.push().setValue(student)
            .addOnSuccessListener {
                _isSaveStudentSucceeded.postValue(true)
            }.addOnFailureListener {
                val errorMessage = it.message.orEmpty()
                _isSaveStudentFailed.postValue(errorMessage)
            }
    }
}