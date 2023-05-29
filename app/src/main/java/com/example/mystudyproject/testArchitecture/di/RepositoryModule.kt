package com.example.mystudyproject.testArchitecture.di

import com.example.mystudyproject.testArchitecture.user.ServiceUser
import com.example.mystudyproject.testArchitecture.user.impl.RepositoryUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {


    @Provides
    @ViewModelScoped
    fun providesRepositoryUser(
        serviceUser: ServiceUser
    ): RepositoryUser {
        return RepositoryUser(serviceUser)
    }
}