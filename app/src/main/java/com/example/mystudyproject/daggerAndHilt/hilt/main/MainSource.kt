package com.example.mystudyproject.daggerAndHilt.hilt.main

import com.example.mystudyproject.daggerAndHilt.hilt.main.entity.GetItemResponseEntity

interface MainSource {

    suspend fun getItemByName(itemName: String): GetItemResponseEntity

}