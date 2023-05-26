package com.example.mystudyproject.navigationTabs.di

import com.example.mystudyproject.navigationTabs.Repository
import com.example.mystudyproject.navigationTabs.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}