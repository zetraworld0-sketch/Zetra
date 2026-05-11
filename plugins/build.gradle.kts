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
}

dependencies {

    // Android Gradle Plugin
    implementation("com.android.tools.build:gradle:8.5.0")

    // Kotlin Gradle Plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")

    // Kover (code coverage)
    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.5")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-appdistribution-gradle:4.0.0")

    // Required internal classpath trick (keep this)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    // Dependency analysis
    implementation("com.autonomousapps:dependency-analysis-gradle-plugin:1.32.0")

    // Metro plugin (requires Java 21 → your CI already fixed this)
    implementation("dev.zacsweers.metro:gradle-plugin:0.13.2")

    // KSP
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.0-1.0.21")

    // ❌ REMOVED (this was breaking Gradle)
    // implementation(libs.compose.compiler.plugin)
}
