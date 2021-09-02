package my.com.m1.onegold.shared.di

import my.com.m1.onegold.shared.di.modules.applicationSharedViewModel

object ApplicationSharedInjector {
    fun provideDependencies() = listOf(
        applicationSharedViewModel
    )
}