package com.example.mystudyproject.testArchitecture.extension

import com.example.mystudyproject.testArchitecture.models.ModelUser
import com.example.mystudyproject.testArchitecture.user.impl.ResponseUser

fun ResponseUser.toModelUser(): ModelUser {

    return ModelUser(
        name = data.itemsByName.firstOrNull()?.name ?: "",
        width = data.itemsByName.firstOrNull()?.width ?: 0,
        height = data.itemsByName.firstOrNull()?.height ?: 0,
        iconLink = data.itemsByName.firstOrNull()?.iconLink ?: "",
        basePrice = data.itemsByName.firstOrNull()?.basePrice ?: 0,
        bestSourcePrice = 0,
        source = "0"
    )
}