package com.example.mystudyproject.daggerAndHilt.hilt

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataBase @Inject constructor(){
    val dataBase: String = "DataBase"
}