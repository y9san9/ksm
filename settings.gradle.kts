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
    "pipeline", "state", "router", "telegram", "kotlinx-serialization-json"
)

// Deprecated
//include(
//    "core", "ktgbotapi",
//)
