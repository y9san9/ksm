plugins {
    kotlin("multiplatform")
    id("publication-convention")
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
