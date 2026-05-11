/*
 * Copyright (c) 2025 Element Creations Ltd.
 * Copyright 2022-2025 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */
plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
dependencies {
    implementation("com.android.tools.build:gradle:8.5.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")

    implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.5")

    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-appdistribution-gradle:4.0.0")

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation("com.autonomousapps:dependency-analysis-gradle-plugin:1.32.0")
    implementation("dev.zacsweers.metro:gradle-plugin:0.13.2")
    implementation("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:2.0.0-1.0.21")

    // ✅ IMPORTANT: REMOVE this if still failing
    // implementation(libs.compose.compiler.plugin)
}
