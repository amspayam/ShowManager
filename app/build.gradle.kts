plugins {
    id("combyne-application-plugin")
}

dependencies {

    // Modules
    implementation(project(":core:base"))
    implementation(project(":core:uikit"))
    implementation(project(":core:cachemanager"))
    implementation(project(":core:repository"))
    implementation(project(":core:navigation"))

    // Features
    implementation(project(":main"))
    implementation(project(":showmanager"))

    // Kotlin stdlib
    implementation(Libraries.kotlin_stdlib)

    // MultiDex
    implementation(Libraries.androidx_multiDex)

    // KTX
    implementation(Libraries.androidx_core_ktx)

    koinDG()
    rxDG()
    coroutinesDG()
    retrofitAndGsonDG()
    navigationDG()
    androidXViewDG()

}

apollo {
    generateKotlinModels.set(true)
}