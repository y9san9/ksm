plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(libs.kotlinx.coroutines.core)
    commonMainApi(libs.ktgbotapi)
    commonMainApi(libs.aqueue)
}
