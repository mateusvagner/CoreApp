package com.pw3.coreapp.ui.students.studentDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.pw3.coreapp.data.DatabaseTables
import com.pw3.coreapp.model.Student
import com.pw3.coreapp.ui.util.SingleLiveEventLiveData

class StudentDetailViewModel: ViewModel() {

    private val studentsDatabaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child(
            DatabaseTables.STUDENTS
        )

    private val _isDeleteStudentSucceeded: MutableLiveData<Boolean> = SingleLiveEventLiveData()
    val isDeleteStudentSucceeded: LiveData<Boolean> = _isDeleteStudentSucceeded

    fun deleteStudent(student: Student) {
        student.id?.let {
            studentsDatabaseReference.child(it).removeValue()
            _isDeleteStudentSucceeded.postValue(true)
        }
    }
}