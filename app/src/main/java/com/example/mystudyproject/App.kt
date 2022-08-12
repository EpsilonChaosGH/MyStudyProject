package com.example.mystudyproject

import android.app.Application
import com.example.mystudyproject.mvvm.DroidService
import com.example.mystudyproject.recyclerView.UsersService

class App: Application() {
    val usersService = UsersService()
    val droidsService = DroidService()
}