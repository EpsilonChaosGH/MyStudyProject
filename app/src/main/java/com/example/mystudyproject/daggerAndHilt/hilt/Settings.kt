package com.example.mystudyproject.daggerAndHilt.hilt

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Settings @Inject constructor(
    @ApplicationContext val context: Context
) {
    val settings: String = "Settings"
}