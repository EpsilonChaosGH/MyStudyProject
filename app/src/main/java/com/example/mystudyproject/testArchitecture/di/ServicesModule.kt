package com.example.mystudyproject.testArchitecture.di

import com.example.mystudyproject.testArchitecture.user.ServiceUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun providesServiceUser(retrofit: Retrofit): ServiceUser {
        return retrofit.create(ServiceUser::class.java)
    }
}