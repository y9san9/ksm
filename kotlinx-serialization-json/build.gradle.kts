plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainImplementation(projects.pipeline)
    commonMainApi(projects.telegram)
    commonMainApi(libs.kotlinx.serialization.json)
}
