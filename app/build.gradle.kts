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

    buildFeatures {
        buildConfig = true
        compose = true
    }

    // ✅ FIXED VERSION
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {

    // ✅ Compose BOM (manages all compose versions)
    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)

    // Core
    implementation("androidx.core:core-ktx:1.13.1")

    // Material XML
    implementation("com.google.android.material:material:1.12.0")

    // ✅ Required for setContent
    implementation("androidx.activity:activity-compose:1.9.0")

    // Compose
    implementation("androidx.compose.material3:material3")

    // ✅ Required for repeatOnLifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Others
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.jakewharton.timber:timber:5.0.1")
}
```
