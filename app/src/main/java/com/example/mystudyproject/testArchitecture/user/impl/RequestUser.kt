package com.example.mystudyproject.testArchitecture.user.impl

import com.squareup.moshi.Json

data class RequestUser(private val item: String) {
    @field:Json(name = "query")
    val queryString = """
{
  itemsByName(name: "$item") {
    name
    types
    avg24hPrice
    basePrice
    width
    height
    changeLast48hPercent
    iconLink
    link
    sellFor {
      price
      source
    }
  }
}
        """.trimIndent()
}