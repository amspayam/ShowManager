package com.combyne.extenstion

import com.android.build.api.dsl.ProductFlavor
import com.android.build.gradle.internal.dsl.BuildType

object Variable {
    const val string = "String"
    const val int = "int"
    const val boolean = "boolean"
}

@Suppress("UnstableApiUsage")
fun ProductFlavor.addConfigField(key: String, value: Any) {
    this.buildConfigField(getTypeString(value), key, value.toString())
}

fun BuildType.addConfigField(key: String, value: Any) {
    this.buildConfigField(getTypeString(value), key, value.toString())
}

fun getTypeString(value: Any): String {
    return when (value) {
        is String -> Variable.string
        is Int -> Variable.int
        else -> Variable.string
    }
}