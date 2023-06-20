package com.pw3.coreapp.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.pw3.coreapp.data.DatabaseTables
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesFirebaseReference(): DatabaseReference = FirebaseDatabase.getInstance().reference

    @StudentsDatabaseReference
    @Provides
    @Singleton
    fun providesStudentsDatabaseReference(firebaseReference: DatabaseReference): DatabaseReference =
        firebaseReference.child(DatabaseTables.STUDENTS)
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StudentsDatabaseReference