plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.telegram)
    commonMainApi(libs.kotlinx.serialization.json)
}
