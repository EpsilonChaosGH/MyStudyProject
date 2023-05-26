package com.example.mystudyproject.hilt.retrofit

import com.example.mystudyproject.hilt.retrofit.entity.GetItemResponseEntity

interface MainSource {

    suspend fun getItemByName(itemName: String): GetItemResponseEntity

}