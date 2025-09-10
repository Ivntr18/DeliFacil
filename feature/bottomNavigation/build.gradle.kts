plugins {
    id("delifacil-compose-feature")
}

android {
    namespace = "com.alimsoft.bottomnavigation"
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":navigator"))
    implementation(project(":common:ui"))
    implementation(project(":feature:order"))
    implementation(project(":feature:menu"))

    implementation(libs.timber)
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}