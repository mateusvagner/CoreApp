package com.pw3.coreapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.pw3.coreapp.data.repository.UserRepository
import com.pw3.coreapp.data.repository.impl.FirebaseUserRepository
import com.pw3.coreapp.ui.util.SingleLiveEventLiveData

class MainViewModel : ViewModel() {

    private val userRepository: UserRepository = FirebaseUserRepository()

    private val _isUserAuthenticated: MutableLiveData<Boolean> = SingleLiveEventLiveData()
    val isUserAuthenticated: LiveData<Boolean> = _isUserAuthenticated


    init {
        _isUserAuthenticated.value = userRepository.isUserAuthenticated()
    }

    fun logoutUser() {
        userRepository.logoutUser()
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        completeListener: OnCompleteListener<AuthResult>
    ) {
        userRepository.signUpWithEmailAndPassword(email, password, completeListener)
    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        completeListener: OnCompleteListener<AuthResult>
    ) {
        userRepository.signInWithEmailAndPassword(email, password, completeListener)
    }
}