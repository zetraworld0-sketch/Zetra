/*
 * Copyright (c) 2025 Element Creations Ltd.
 * Copyright 2022-2025 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 */

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {

    // ✅ FIX: Force compatible Android Gradle Plugin
    implementation("com.android.tools.build:gradle:8.4.0")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-appdistribution-gradle:4.0.0")

    // KSP
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.0-1.0.24")

    // Compose compiler plugin (required for Kotlin 2.0)
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.0.0")

    // Dependency analysis (safe version)
    implementation("com.autonomousapps:dependency-analysis-gradle-plugin:1.32.0")

    // Metro plugin (keep your original)
    implementation("dev.zacsweers.metro:gradle-plugin:0.13.2")
}
