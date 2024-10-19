plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainImplementation(projects.pipeline)
    commonMainImplementation(projects.state)
    commonMainImplementation(projects.router)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.ktgbotapi)
    commonMainImplementation(libs.aqueue)
}
