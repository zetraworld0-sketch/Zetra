android {
    namespace = "com.netscape.messaging"

    defaultConfig {
        applicationId = BuildTimeConfig.APPLICATION_ID
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        ndk {
            abiFilters += listOf("armeabi-v7a", "x86", "arm64-v8a", "x86_64")
        }

        splits {
            abi {
                val buildingAppBundle = gradle.startParameter.taskNames.any { it.contains("bundle") }
                isEnable = !buildingAppBundle
                reset()

                if (!buildingAppBundle) {
                    include("armeabi-v7a", "x86", "arm64-v8a", "x86_64")
                    isUniversalApk = true
                }
            }
        }

        androidResources {
            localeFilters += locales
        }
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storeFile = file("./signature/debug.keystore")
            storePassword = "android"
        }
        register("nightly") {
            keyAlias = System.getenv("ELEMENT_ANDROID_NIGHTLY_KEYID")
                ?: project.property("signing.element.nightly.keyId") as? String?
            keyPassword = System.getenv("ELEMENT_ANDROID_NIGHTLY_KEYPASSWORD")
                ?: project.property("signing.element.nightly.keyPassword") as? String?
            storeFile = file("./signature/nightly.keystore")
            storePassword = System.getenv("ELEMENT_ANDROID_NIGHTLY_STOREPASSWORD")
                ?: project.property("signing.element.nightly.storePassword") as? String?
        }
    }

    // ✅ CHANGED HERE
    val baseAppName = "Netscape Messaging"

    val buildType = if (isEnterpriseBuild) "Enterprise" else "FOSS"
    logger.warnInBox("Building ${defaultConfig.applicationId} ($baseAppName) [$buildType]")

    buildTypes {
        val oAuthRedirectSchemeBase = BuildTimeConfig.METADATA_HOST_REVERSED ?: "io.element.android"

        getByName("debug") {
            resValue("string", "app_name", "$baseAppName dbg")
            resValue(
                "string",
                "login_redirect_scheme",
                "$oAuthRedirectSchemeBase.debug",
            )
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            resValue("string", "app_name", baseAppName)
            resValue(
                "string",
                "login_redirect_scheme",
                oAuthRedirectSchemeBase,
            )
            signingConfig = signingConfigs.getByName("debug")

            optimization {
                enable = true
                keepRules {
                    files.add(File(projectDir, "common-proguard-rules.pro"))
                    files.add(getDefaultProguardFile("proguard-android-optimize.txt"))

                    val flavorProguardFile = if (isEnterpriseBuild) {
                        File(projectDir, "enterprise-proguard-rules.pro")
                    } else {
                        File(projectDir, "default-proguard-rules.pro")
                    }

                    if (flavorProguardFile.exists()) {
                        files.add(flavorProguardFile)
                    } else {
                        logger.warn("Proguard file ${flavorProguardFile.absolutePath} does not exist")
                    }
                }
            }
        }

        register("nightly") {
            val release = getByName("release")
            initWith(release)
            applicationIdSuffix = ".nightly"
            versionNameSuffix = "-nightly"
            resValue("string", "app_name", "$baseAppName nightly")
            resValue(
                "string",
                "login_redirect_scheme",
                "$oAuthRedirectSchemeBase.nightly",
            )
            matchingFallbacks += listOf("release")
            signingConfig = signingConfigs.getByName("nightly")

            firebaseAppDistribution {
                artifactType = "APK"
                artifactPath = "$rootDir/app/build/outputs/apk/gplay/nightly/app-gplay-universal-nightly.apk"
                releaseNotesFile = "tools/release/ReleaseNotesNightly.md"
                groups = if (isEnterpriseBuild) {
                    "enterprise-testers"
                } else {
                    "external-testers"
                }
                appId = if (isEnterpriseBuild) {
                    "1:912726360885:android:3f7e1fe644d99d5a00427c"
                } else {
                    "1:912726360885:android:e17435e0beb0303000427c"
                }
            }
        }
    }

    buildFeatures {
        buildConfig = true
    }

    flavorDimensions += "store"

    productFlavors {
        create("gplay") {
            dimension = "store"
            isDefault = true
            buildConfigFieldStr("SHORT_FLAVOR_DESCRIPTION", "G")
            buildConfigFieldStr("FLAVOR_DESCRIPTION", "GooglePlay")
        }
        create("fdroid") {
            dimension = "store"
            buildConfigFieldStr("SHORT_FLAVOR_DESCRIPTION", "F")
            buildConfigFieldStr("FLAVOR_DESCRIPTION", "FDroid")
        }
    }

    packaging {
        resources.pickFirsts += setOf(
            "META-INF/versions/9/OSGI-INF/MANIFEST.MF",
        )

        jniLibs {
            useLegacyPackaging = project.findProperty("useLegacyPackaging")?.toString()?.toBoolean()
        }
    }
}
