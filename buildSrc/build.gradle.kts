plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    //implementation(project(":gradle-plugins"))
    implementation(libs.plugin.android)
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.ktlint)
    implementation(libs.plugin.versions)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}