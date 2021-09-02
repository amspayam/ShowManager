package com.combyne.extenstion

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.group(vararg group: String) {
    group.map {
        "implementation"(it)
    }
}
