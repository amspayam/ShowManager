plugins {
    id("combyne-library-plugin")
}

dependencies {

    // Modules
    implementation(project(":core:base"))
    implementation(project(":core:cachemanager"))

    koinDG()
    rxDG()
    apolloDG()
    coroutinesDG()
    retrofitAndGsonDG()
    chuckDG()

}