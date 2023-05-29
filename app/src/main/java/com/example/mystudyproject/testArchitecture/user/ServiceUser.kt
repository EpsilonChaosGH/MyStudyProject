package com.example.mystudyproject.testArchitecture.user

import com.example.mystudyproject.testArchitecture.user.impl.RequestUser
import com.example.mystudyproject.testArchitecture.user.impl.ResponseUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ServiceUser {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("./")
    suspend fun getItemByName(
        @Body body: RequestUser
    ): Response<ResponseUser>
}