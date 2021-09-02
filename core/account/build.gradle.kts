plugins {
    id("combyne-library-plugin")
    id("kotlin-android")
}

dependencies {

    // Modules
    implementation(project(":core:base"))

    baseModuleDG()

}