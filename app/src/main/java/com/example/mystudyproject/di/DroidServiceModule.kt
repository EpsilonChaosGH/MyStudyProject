package com.example.mystudyproject.di

import com.example.mystudyproject.mvvm.DroidService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DroidServiceModule {

    @Provides
    fun providesDroidService(): DroidService = DroidService()
}