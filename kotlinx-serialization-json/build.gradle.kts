plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.core)
    commonMainApi(libs.kotlinx.serialization.json)
}
