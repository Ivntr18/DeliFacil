plugins {
    id("com.android.application")
    kotlin("android")
    id("delifacil-ktlint")
}

android {
    namespace = "com.alimsoft.delifacil"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.alimsoft.delifacil"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
            proguardFiles.add(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFiles.add(File("proguard-rules.pro"))
        }
    }
    compileOptions {
        val jvmVersion = libs.versions.jvm.get()
        kotlinOptions {
            jvmTarget = jvmVersion
        }
        sourceCompatibility = JavaVersion.toVersion(jvmVersion)
        targetCompatibility = JavaVersion.toVersion(jvmVersion)
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/NOTICE")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/ASL2.0")
        resources.excludes.add("META-INF/*.kotlin_module")
    }
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(project(":domain"))
    implementation(project(":common:core"))
    implementation(project(":common:ui"))
    implementation(project(":navigator"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:bottomNavigation"))
    implementation(project(":feature:order"))
    implementation(project(":feature:menu"))

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.koin)
    implementation(libs.compose.navigation)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.timber)
    implementation(libs.timber.slf4j)

    debugImplementation(libs.bundles.compose.debug)
    debugImplementation(libs.okhttp.logging)
    debugImplementation(libs.okhttp.logging.curl)
    debugImplementation(libs.process.phoenix)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(libs.compose.ui.test.junit)
}