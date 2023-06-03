@file:Suppress("unused")

package com.example.internal.dependencies

import com.example.internal.Versions

object Test {
    /**
     * [JUnit](https://mvnrepository.com/artifact/junit/junit)
     * JUnit is a unit testing framework for Java, created by Erich Gamma and Kent Beck.
     */
    const val junit = "junit:junit:${Versions.junit}"

    /**
     * [Mockk](https://github.com/mockk/mockk)
     */
    const val mockk = "io.mockk:mockk-android:${Versions.mockk}"
}