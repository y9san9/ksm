plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainApi(projects.telegram.telegramCore)
    commonMainImplementation(projects.telegram.telegramPlugin)

    commonMainApi(libs.kotlinx.coroutines.core)
    commonMainApi(libs.ktgbotapi)
    commonMainApi(libs.aqueue)

    commonTestImplementation(projects.kotlinxSerializationJson)
}
