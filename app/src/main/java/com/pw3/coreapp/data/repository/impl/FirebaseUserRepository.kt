package com.pw3.coreapp.data.repository.impl

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pw3.coreapp.data.repository.UserRepository

class FirebaseUserRepository: UserRepository {

    private val auth: FirebaseAuth = Firebase.auth

    override fun isUserAuthenticated(): Boolean {
        return auth.currentUser != null
    }

    override fun logoutUser() {
        auth.signOut()
    }

    override fun signInWithEmailAndPassword(
        email: String,
        password: String,
        completeListener: OnCompleteListener<AuthResult>
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(completeListener)
    }

    override fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        completeListener: OnCompleteListener<AuthResult>
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(completeListener)
    }
}