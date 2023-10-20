// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.mapsplatform.secrets) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics.plugin) apply false
    alias(libs.plugins.firebase.performance.plugin) apply false
    alias(libs.plugins.catalog.update)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
