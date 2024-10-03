plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(projects.pipeline)
    commonMainImplementation(projects.state)
    commonMainImplementation(projects.router)
}
