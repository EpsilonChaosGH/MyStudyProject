package com.example.mystudyproject

import android.app.Application
import com.example.mystudyproject.mvvm.DroidService
import com.example.mystudyproject.navigationTabs.RepositoryImpl
import com.example.mystudyproject.recyclerView.UsersService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    val usersService = UsersService()
    val droidsService = DroidService()
    val repositoryImpl = RepositoryImpl()
}