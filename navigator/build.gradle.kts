plugins {
    id("delifacil-android-lib")
}

android {
    namespace = "com.alimsoft.navigation"
}

dependencies {
    api(libs.compose.navigation)
    api(libs.koin.android)
    api(libs.compose.runtime)

    implementation(libs.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}
