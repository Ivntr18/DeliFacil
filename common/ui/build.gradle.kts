plugins {
    id("delifacil-compose-feature")
}

android {
    namespace = "com.alimsoft.common.ui"
}

dependencies {
    api(project(":domain"))
    api(project(":resources:strings"))
    api(project(":resources:drawables"))
    implementation(project(":navigator"))

    api(libs.bundles.androidx)
    api(libs.bundles.compose)
    api(libs.bundles.compose.feature)
    api(libs.bundles.koin)
    api(libs.coil.compose)
    api(libs.coil.gif)
    api(libs.kotlinx.coroutines.core)
    api(libs.androidx.constraint.compose)
    debugApi(libs.bundles.compose.debug)

    testImplementation(libs.bundles.unittest.android)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}