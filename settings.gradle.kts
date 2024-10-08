enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ksm"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}

includeBuild("build-logic")

include(
    "pipeline", "state", "router",
    "fsm",
    "core", "ktgbotapi", "kotlinx-serialization-json"
)
