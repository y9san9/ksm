plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.pipeline)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.aqueue)
    commonTestImplementation(kotlin("test"))
    commonTestImplementation(libs.kotlinx.coroutines.test)
}
