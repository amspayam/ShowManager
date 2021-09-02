package com.combyne.plugins

import com.combyne.config.blocks.setupAndroidBlock
import com.combyne.config.blocks.setupBuildTypesBlock
import com.combyne.config.blocks.setupFlavorBlock
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class CombyneLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) =
        with(project) {
            applyPlugins()
            androidConfig()
        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("com.android.library")
            apply("kotlin-android")
            apply("com.github.ben-manes.versions")
            apply("com.apollographql.apollo")
            apply("androidx.navigation.safeargs")
        }
    }

    private fun Project.androidConfig() {

        setupAndroidBlock(isApplication = false)
        setupBuildTypesBlock(isApplication = false)
        setupFlavorBlock(isApplication = false)

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}