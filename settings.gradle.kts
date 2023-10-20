pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "DeliFacil"
rootProject.buildFileName = "build.gradle.kts"
include(
    ":app",
    ":common:core",
    ":common:ui",
    ":domain",
    ":feature:home",
    ":feature:login",
    ":navigator",
    ":resources:drawables",
    ":resources:strings",
)
