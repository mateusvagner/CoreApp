package com.pw3.coreapp.ui.students.editStudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.pw3.coreapp.data.DatabaseTables
import com.pw3.coreapp.model.Student
import com.pw3.coreapp.ui.util.SingleLiveEventLiveData

class EditStudentViewModel: ViewModel() {

    private val studentsDatabaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child(
            DatabaseTables.STUDENTS
        )

    private val _isUpdateStudentSucceeded: MutableLiveData<Boolean> = SingleLiveEventLiveData()
    val isUpdateStudentSucceeded: LiveData<Boolean> = _isUpdateStudentSucceeded

    private val _isSaveStudentFailed: MutableLiveData<String> = SingleLiveEventLiveData()
    val isSaveStudentFailed: LiveData<String> = _isSaveStudentFailed

    fun updateStudent(student: Student) {
        studentsDatabaseReference.child(student.id!!).setValue(student)
        _isUpdateStudentSucceeded.postValue(true)
    }
}
