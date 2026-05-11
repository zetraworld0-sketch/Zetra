plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // ✅ REQUIRED for Kotlin 2.0 + Compose
    id("org.jetbrains.kotlin.plugin.compose")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {

    // Core
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")

    // Material
    implementation("com.google.android.material:material:1.12.0")

    // ✅ Compose BOM (fix version issues)
    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // ✅ Fix setContent
    implementation("androidx.activity:activity-compose:1.9.0")

    // ✅ Fix repeatOnLifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Logs
    implementation("com.jakewharton.timber:timber:5.0.1")
}
