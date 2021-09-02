package com.combyne.plugins

import com.combyne.config.blocks.setupAndroidBlock
import com.combyne.config.blocks.setupBuildTypesBlock
import com.combyne.config.blocks.setupFlavorBlock
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class CombyneApplicationPlugin : Plugin<Project> {

    override fun apply(project: Project) =
        with(project) {
            applyPlugins()
            androidConfig()
        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("com.android.application")
            apply("kotlin-android")
            apply("com.github.ben-manes.versions")
            apply("com.apollographql.apollo")
            apply("androidx.navigation.safeargs")
        }
    }

    private fun Project.androidConfig() {

        setupAndroidBlock(isApplication = true)
        setupBuildTypesBlock(isApplication = true)
        setupFlavorBlock(isApplication = true)

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

}