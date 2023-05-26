package com.example.mystudyproject.hilt.retrofit

import com.example.mystudyproject.hilt.retrofit.entity.GetItemRequestEntity
import com.example.mystudyproject.hilt.retrofit.entity.GetItemResponseEntity
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface MainApi {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("./")
    suspend fun getItemByName(
        @Body body: GetItemRequestEntity
    ): GetItemResponseEntity
}