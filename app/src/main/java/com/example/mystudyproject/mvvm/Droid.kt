package com.example.mystudyproject.mvvm

data class Droid(
    val id: Long,
    val photo: String,
    val name: String,
    val company: String
)


data class DroidDetails(
    val droid: Droid,
    val details: String
)