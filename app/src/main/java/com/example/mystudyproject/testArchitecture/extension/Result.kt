/*
 * Copyright 2021 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mystudyproject.testArchitecture.extension

import com.example.mystudyproject.testArchitecture.ResponseResult


val ResponseResult<*>?.isSucceeded get() = this != null && this is ResponseResult.Success && data != null

val ResponseResult<*>?.isError get() = this != null && this is ResponseResult.Error

inline infix fun <T, Value : Any> ResponseResult<T>?.runSucceeded(predicate: (data: T) -> Value): Value? {
    if (this != null && this.isSucceeded && this is ResponseResult.Success && this.data != null) {
        return predicate.invoke(this.data)
    }
    return null
}

inline infix fun <T> ResponseResult<T>.success(predicate: (data: T) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Success && this.data != null) {
        predicate.invoke(this.data)
    }
    return this
}

inline infix fun <T> ResponseResult<T>.error(predicate: (data: Exception) -> Unit) {
    if (this is ResponseResult.Error) {
        predicate.invoke(this.exception)
    }
}