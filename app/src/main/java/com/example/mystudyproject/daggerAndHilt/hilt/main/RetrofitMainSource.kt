package com.example.mystudyproject.daggerAndHilt.hilt.main

import com.example.mystudyproject.daggerAndHilt.hilt.main.entity.GetItemRequestEntity
import com.example.mystudyproject.daggerAndHilt.hilt.main.entity.GetItemResponseEntity
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitMainSource @Inject constructor(
    private val retrofit: Retrofit,
    val moshi: Moshi
) : MainSource {

    private val mainApi = retrofit.create(MainApi::class.java)

    override suspend fun getItemByName(itemName: String): GetItemResponseEntity {
        return  mainApi.getItemByName(
            GetItemRequestEntity(itemName)
        )
    }
}