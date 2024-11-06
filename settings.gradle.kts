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
    "pipeline",
    "telegram:telegram-plugin",
    "telegram:telegram-core",
    "kotlinx-serialization-json"
)

// Deprecated
//include(
//    "core", "ktgbotapi",
//)
