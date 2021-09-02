package my.com.m1.repository.di


import my.com.m1.repository.network.NetworkCallbackImpl
import my.com.m1.repository.network.NetworkCallbackX
import my.com.m1.repository.network.NetworkManager
import org.koin.dsl.module

object RepositoryInjector {

    fun provideDependencies() = module {

        single { NetworkManager(get(), get(), get(), get()) }

        single<NetworkCallbackX> { NetworkCallbackImpl(get(), get(), get()) }

    }
}