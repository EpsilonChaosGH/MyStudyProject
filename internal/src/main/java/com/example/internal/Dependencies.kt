@file:Suppress("unused")

package com.example.internal

import com.example.internal.dependencies.Di
import com.example.internal.dependencies.Other
import com.example.internal.dependencies.Test
import com.example.internal.dependencies.Retrofit
import com.example.internal.dependencies.Room

object Dependencies {
    val android = Android
    val hilt = Di
    val room = Room
    val retrofit = Retrofit
    val other = Other
    val test = Test
}