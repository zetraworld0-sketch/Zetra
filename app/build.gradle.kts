```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.netscape.messaging"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.netscape.messaging"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
        }
    }

    // ✅ ENABLE COMPOSE (fixes setContent)
    buildFeatures {
        buildConfig = true
        compose = true
    }

    // ✅ REQUIRED for Compose
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}

dependencies {

    // Core
    implementation("androidx.core:core-ktx:1.13.1")

    // Material (XML UI)
    implementation("com.google.android.material:material:1.12.0")

    // ✅ REQUIRED for setContent
    implementation("androidx.activity:activity-compose:1.9.0")

    // Compose
    implementation("androidx.compose.material3:material3:1.2.1")

    // ✅ REQUIRED for repeatOnLifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // AppCompat
    implementation("androidx.appcompat:appcompat:1.7.0")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")
}
```
