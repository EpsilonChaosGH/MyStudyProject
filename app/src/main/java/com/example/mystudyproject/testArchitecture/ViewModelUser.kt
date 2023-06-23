package com.example.mystudyproject.testArchitecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystudyproject.testArchitecture.extension.success
import com.example.mystudyproject.testArchitecture.models.ModelUser
import com.example.mystudyproject.testArchitecture.user.impl.RepositoryUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(
    private val repositoryUser: RepositoryUser,
    private val preferences: AppPreferences,
) : ViewModel() {

    init {
        repeatLoadingUser()
    }

    private val _loadingUser: MutableStateFlow<ResponseResult<ModelUser>?> = MutableStateFlow(null)
    val loadingUser = _loadingUser.asStateFlow()

    private val _loadingUserPref: MutableStateFlow<ModelUser?> = MutableStateFlow(null)
    val loadingUserPref = _loadingUserPref.asStateFlow()

    fun repeatLoadingUser() {
        executeWithResponse {
            repositoryUser.loadingUser()
        }
        repositoryUser.loadingUser()
            .onEach {
                it.success { preferences.modelUser = it }
                _loadingUser.value = it
                _loadingUserPref.value = preferences.modelUser
            }
            .launchIn(viewModelScope)
    }
}