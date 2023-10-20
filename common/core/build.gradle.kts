plugins {
    id("delifacil-android-lib")
}

android {
    namespace = "com.alimsoft.common.core"
}

dependencies {
    api(project(":resources:strings"))
    api(project(":resources:drawables"))

    api(libs.bundles.androidx)
    api(libs.bundles.koin)
    api(libs.kotlinx.coroutines.core)

    testImplementation(libs.bundles.unittest.android)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}