package com.pw3.coreapp.ui.students.allStudents

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pw3.coreapp.data.DatabaseTables
import com.pw3.coreapp.model.Student
import com.pw3.coreapp.ui.util.SingleLiveEventLiveData
import com.pw3.coreapp.ui.util.filterStudentsBirthDayWithinNext7Days
import com.pw3.coreapp.ui.util.filterStudentsPaymentDayPending
import com.pw3.coreapp.ui.util.filterStudentsPaymentDayWithinNext5Days


class StudentsViewModel : ViewModel() {

    private val studentsDatabaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child(
            DatabaseTables.STUDENTS
        )

    private val _isGetAllStudentsFailed: MutableLiveData<String> = SingleLiveEventLiveData()
    val isGetAllStudentsFailed: LiveData<String> = _isGetAllStudentsFailed

    private val _allStudents = MutableLiveData<List<Student>>()
    val allStudents: LiveData<List<Student>> get() = _allStudents

    private val _studentsUpcomingBirthday = MutableLiveData<List<Student>>()
    val studentsUpcomingBirthday: LiveData<List<Student>> get() = _studentsUpcomingBirthday

    private val _studentsPaymentDueDate = MutableLiveData<List<Student>>()
    val studentsPaymentDueDate: LiveData<List<Student>> get() = _studentsPaymentDueDate

    init {
        studentsDatabaseReference.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val allStudents: ArrayList<Student> = arrayListOf()
                for (ds in dataSnapshot.children) {
                    val student: Student? = ds.getValue(Student::class.java)
                    student?.id = ds.key
                    student?.let {
                        allStudents.add(it)
                    }
                }

                buildSectionLists(allStudents)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                val message = databaseError.message
                _isGetAllStudentsFailed.postValue(message)
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildSectionLists(students: List<Student>) {
        val upcomingBirthDay = students.filterStudentsBirthDayWithinNext7Days()

        val paymentDueDate =
            students.filterStudentsPaymentDayWithinNext5Days() + students.filterStudentsPaymentDayPending()

        _allStudents.postValue(students)
        _studentsUpcomingBirthday.postValue(upcomingBirthDay)
        _studentsPaymentDueDate.postValue(paymentDueDate.sortedBy { it.paymentDueDate?.toDouble() })
    }
}
