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

    // 🔥 REQUIRED for Theme.Material3 XML (THIS FIXES YOUR ERROR)
    implementation("com.google.android.material:material:1.12.0")

    // Compose (safe to keep)
    implementation("androidx.compose.material3:material3:1.2.1")

    // Splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // AppCompat (required for compatibility)
    implementation("androidx.appcompat:appcompat:1.7.0")

    // Timber (for logs)
    implementation("com.jakewharton.timber:timber:5.0.1")
}
