package com.pw3.coreapp.ui.students.studentDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pw3.coreapp.model.Student
import com.pw3.coreapp.ui.util.SingleLiveEventLiveData

class StudentDetailViewModel: ViewModel() {

    private val _isDeleteStudentSucceeded: MutableLiveData<Boolean> = SingleLiveEventLiveData()
    val isDeleteStudentSucceeded: LiveData<Boolean> = _isDeleteStudentSucceeded

    fun deleteStudent(student: Student) {

    }
}