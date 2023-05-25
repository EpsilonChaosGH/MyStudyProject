package com.example.mystudyproject.daggerAndHilt.hilt

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(): Repository {

    override val repositoryValue: String = "RepositoryImpl"

}