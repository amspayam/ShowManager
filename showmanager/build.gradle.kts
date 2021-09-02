plugins {
    id("combyne-library-plugin")
    id("kotlin-android")
}

dependencies {

    // Modules
    implementation(project(":core:base"))
    implementation(project(":core:account"))
    implementation(project(":core:uikit"))
    implementation(project(":core:shared"))
    implementation(project(":core:cachemanager"))
    implementation(project(":core:repository"))
    implementation(project(":core:navigation"))

    baseModuleDG()

}