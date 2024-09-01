plugins {
    kotlin("multiplatform")
    id("publication-convention")
    kotlin("plugin.serialization")
}

kotlin {
    explicitApi()

    jvmToolchain(17)

    jvm()

    js(IR) {
        browser()
        nodejs()
    }
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.core)
    commonMainApi(projects.kotlinxSerializationJson)
    commonMainImplementation(libs.ktgbotapi)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.aqueue)
    commonTestImplementation(kotlin("test"))
    commonTestImplementation(libs.kotlinx.coroutines.test)
}
