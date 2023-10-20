plugins {
    id("delifacil-compose-feature")
}

android {
    namespace = "com.alimsoft.home"
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":navigator"))
    implementation(project(":common:ui"))

    implementation(libs.timber)
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}