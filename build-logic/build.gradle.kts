plugins {
    `kotlin-dsl`
}

dependencies {
    api(libs.kotlin.gradle.plugin)
    api(libs.kotlinx.serialization.gradle.plugin)
    api(libs.maven.publish.gradle.plugin)
}
