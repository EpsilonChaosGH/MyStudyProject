package com.example.mystudyproject.testlogger.utils


import com.example.mystudyproject.testlogger.model.ClientException
import com.example.mystudyproject.testlogger.model.ErrorType
import com.example.mystudyproject.testlogger.model.GenericException
import com.example.mystudyproject.testlogger.model.ServerException
import com.example.mystudyproject.testlogger.model.UnauthorizedException
import java.io.IOException
import java.net.HttpURLConnection
import java.text.SimpleDateFormat
import java.util.Date



private fun getDate(utcInMillis: Long, formatPattern: String): String {
    val sdf = SimpleDateFormat(formatPattern)
    val dateFormat = Date(utcInMillis * 1000)
    return sdf.format(dateFormat)
}

fun mapResponseCodeToThrowable(code: Int): Throwable = when (code) {
    HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedException("Unauthorized access : $code")
    in 400..499 -> ClientException("Client error : $code")
    in 500..600 -> ServerException("Server error : $code")
    else -> GenericException("Generic error : $code")
}

fun mapThrowableToErrorType(throwable: Throwable): ErrorType {
    val errorType = when (throwable) {
        is IOException -> ErrorType.IO_CONNECTION
        is ClientException -> ErrorType.CLIENT
        is ServerException -> ErrorType.SERVER
        is UnauthorizedException -> ErrorType.UNAUTHORIZED
        else -> ErrorType.GENERIC
    }
    return errorType
}
