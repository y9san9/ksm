plugins {
    id("kmp-library-convention")
}

version = libs.versions.ksm.get()

dependencies {
    commonMainImplementation(projects.pipeline)

//    commonMainApi(projects.telegram.telegramCore)

    commonMainApi(libs.kotlinx.coroutines.core)
    commonMainApi(libs.ktgbotapi)
    commonMainApi(libs.aqueue)

    commonTestImplementation(projects.kotlinxSerializationJson)
}
