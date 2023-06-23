package com.pw3.coreapp.di

import com.pw3.coreapp.data.repository.impl.FirebaseStudentRepository
import com.pw3.coreapp.data.repository.StudentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsStudentRepository(impl: FirebaseStudentRepository): StudentRepository
}
