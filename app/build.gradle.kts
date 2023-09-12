typealias and = com.example.internal.Android
typealias dep = com.example.internal.Dependencies

plugins {
    id("dagger.hilt.android.plugin")
    id("com.android.application")
    id("kotlin-android")
    id("internal")

    kotlin("kapt")
    kotlin("plugin.parcelize")
}

android {
    compileSdk = and.compileSdk

    defaultConfig {
        applicationId = "com.example.mystudyproject"
        minSdk = and.minSdk
        targetSdk = and.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    viewBinding {
        enable = true
    }

    packaging {
        resources {
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
        }
    }
    namespace = "com.example.mystudyproject"
}

dependencies {

    dep.retrofit.apply { // https://square.github.io/retrofit/
        implementation(converterMoshi)
        implementation(retrofit2)
    }

    dep.room.apply { // https://developer.android.com/jetpack/androidx/releases/room
        implementation(runtime)
        implementation(ktx)
        kapt(compiler)
    }

    dep.hilt.apply { // https://dagger.dev/hilt/
        implementation(hiltAndroid)
        kapt(daggerHiltCompiler)
        kaptAndroidTest(daggerHiltCompiler)
    }

    dep.other.apply {// Miscellaneous required libraries
        implementation(workManager)
        implementation(yandexMaps)
        implementation(ktxCore)
        implementation(ktxActivity)
        implementation(ktxFragment)
        implementation(appcompat)
        implementation(constraintLayout)
        implementation(material)
        implementation(navigationFragment)
        implementation(navigationUi)
        implementation(glide)
        implementation(coroutines)
    }

    dep.test.apply { // Unit tests
        testImplementation(junit)
        testImplementation(mockk)
    }
}