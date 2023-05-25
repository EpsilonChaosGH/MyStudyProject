package com.example.mystudyproject.daggerAndHilt.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}