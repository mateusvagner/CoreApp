package com.pw3.coreapp.data.repository

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult

interface UserRepository {

    fun isUserAuthenticated(): Boolean

    fun logoutUser()

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        completeListener: OnCompleteListener<AuthResult>
    )

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        completeListener: OnCompleteListener<AuthResult>
    )
}
