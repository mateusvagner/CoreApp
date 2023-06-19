package com.pw3.coreapp.data.repository

import com.google.firebase.auth.FirebaseUser

interface UserRepository {

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: (FirebaseUser) -> Unit,
        onError: (Exception) -> Unit
    )
}
