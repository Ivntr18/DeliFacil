plugins {
    id("delifacil-compose-feature")
}

android {
    namespace = "com.alimsoft.login"
}

dependencies {
    implementation(project(":common:core"))
    implementation(project(":navigator"))
    implementation(project(":common:ui"))
    implementation(project(":resources:strings"))
    implementation(project(":feature:home"))

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
}