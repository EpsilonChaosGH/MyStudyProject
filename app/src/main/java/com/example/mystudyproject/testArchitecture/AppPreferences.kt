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

package com.example.mystudyproject.testArchitecture

import android.content.SharedPreferences
import com.example.mystudyproject.testArchitecture.models.ModelUser
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class AppPreferences(
    moshi: Moshi,
    private val p: SharedPreferences
) {

    companion object {
        private const val USER = "USER"
        private const val LAST_UPDATE_REPOS = "LAST_UPDATE_REPOS_"
    }

    private val moshiAdapter: JsonAdapter<ModelUser> = moshi.adapter(ModelUser::class.java)


    var lastUpdateRepos: Long
        get() = p.getLong(LAST_UPDATE_REPOS, 0L)
        set(value) = p.edit().putLong(LAST_UPDATE_REPOS, value).apply()

    val name: String
        get() = modelUser!!.name

    var modelUser: ModelUser?
        get() = p.getString(USER, null)?.let { moshiAdapter.fromJson(it) }
        set(value) = value
            ?.let { p.edit().putString(USER, moshiAdapter.toJson(value)).apply() }
            ?: p.edit().remove(USER).apply()
}