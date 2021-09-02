plugins {
    id("combyne-library-plugin")
}

dependencies {

    implementation(project(":core:base"))
    implementation(project(":core:uikit"))
    implementation(project(":core:cachemanager"))
    implementation(project(":core:repository"))
    implementation(project(":core:account"))
    implementation(project(":core:navigation"))
    implementation(project(":core:shared"))


    koinDG()
    rxDG()
    coroutinesDG()
    retrofitAndGsonDG()
    navigationDG()
    chuckDG()
    navigationDG()
    androidXViewDG()

    // Logger
    implementation(Libraries.logger)

}