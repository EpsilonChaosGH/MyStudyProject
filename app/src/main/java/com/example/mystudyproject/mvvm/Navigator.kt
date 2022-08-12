package com.example.mystudyproject.mvvm


interface Navigator {

    fun showDetails(droid: Droid)

    fun goBack()

    fun toast(massageRes: Int)
}