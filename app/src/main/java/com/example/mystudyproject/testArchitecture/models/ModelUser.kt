package com.example.mystudyproject.testArchitecture.models


data class ModelUser(
    val name: String,
    val width: Long,
    val height: Long,
    val iconLink: String,
    var basePrice: Long,
    val bestSourcePrice: Long,
    val source: String
)
