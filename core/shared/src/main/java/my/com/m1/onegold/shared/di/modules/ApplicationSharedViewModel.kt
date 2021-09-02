package my.com.m1.onegold.shared.di.modules


import my.com.m1.onegold.shared.viewmodel.ApplicationShareViewModel
import org.koin.dsl.module

val applicationSharedViewModel = module {
    single { ApplicationShareViewModel() }
}