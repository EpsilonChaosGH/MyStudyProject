package com.example.mystudyproject.testArchitecture.user.impl

import androidx.annotation.WorkerThread
import com.example.mystudyproject.testArchitecture.NotFoundException
import com.example.mystudyproject.testArchitecture.ResponseResult
import com.example.mystudyproject.testArchitecture.extension.toModelUser
import com.example.mystudyproject.testArchitecture.user.ServiceUser
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryUser @Inject constructor(
    private val  service: ServiceUser
) {
    @WorkerThread
    fun loadingUser() = flow {
        try {
            service.getItemByName(RequestUser("akm")).body()?.toModelUser()?.let { model ->
                emit(ResponseResult.Success(model))
            } ?: run {
                throw NotFoundException()
            }
        } catch (ex: Exception) {
            emit(ResponseResult.Error(ex))
        }
    }

}