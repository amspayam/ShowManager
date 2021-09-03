plugins {
    id("combyne-library-plugin")
    id("kotlin-android")
}

dependencies {

    // Modules
    implementation(project(":core:base"))
    implementation(project(":core:uikit"))
    implementation(project(":core:cachemanager"))
    implementation(project(":core:repository"))
    implementation(project(":core:navigation"))

    baseModuleDG()

}