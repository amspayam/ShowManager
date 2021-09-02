plugins {
    id("combyne-library-plugin")
}

dependencies {

    // Modules
    implementation(project(":core:base"))
    implementation(project(":core:cachemanager"))
    implementation(project(":core:account"))
    implementation(project(":core:shared"))

    koinDG()
    rxDG()
    apolloDG()
    coroutinesDG()
    retrofitAndGsonDG()
    chuckDG()

}