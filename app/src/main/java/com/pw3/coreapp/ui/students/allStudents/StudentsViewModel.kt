package com.pw3.coreapp.ui.students.allStudents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Students Fragment"
    }
    val text: LiveData<String> = _text
}
