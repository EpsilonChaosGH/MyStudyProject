/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mystudyproject.testArchitecture.di

import android.content.Context
import com.example.mystudyproject.testArchitecture.AppPreferences
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CommonModule {
    @Provides
    @ViewModelScoped
    fun provideSharedPreferences(
        @ApplicationContext context: Context, moshi: Moshi
    ): AppPreferences {
        return AppPreferences(
            moshi, context.getSharedPreferences("sharedPrefsFile", Context.MODE_PRIVATE)
        )
    }
}