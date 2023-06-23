package com.example.mystudyproject.testArchitecture

sealed class ResponseResult<out R> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Error(val exception: Exception) : ResponseResult<Nothing>()
}

inline fun <T> executeWithResponse(body: () -> T): ResponseResult<T> {
    return try {
        ResponseResult.Success(body.invoke())
    } catch (e: Exception) {
        ResponseResult.Error(e)
    }
}