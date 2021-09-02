package my.com.m1.onegold.account.di

import android.accounts.AccountManager
import my.com.m1.onegold.account.AccountHandlerImpl
import my.com.m1.onegold.account.model.AccountHandler
import my.com.m1.onegold.account.usecase.GetTokenUseCaseSync
import my.com.m1.onegold.account.usecase.RemoveUserAccountUseCase
import my.com.m1.onegold.account.usecase.SaveTokenUseCaseSync
import org.koin.dsl.module

object AccountManagerInjector {

    fun provideDependencies() = module {

        single { AccountManager.get(get()) }

        factory<AccountHandler> { AccountHandlerImpl(get()) }

        factory { GetTokenUseCaseSync(get()) }
        factory { RemoveUserAccountUseCase(get()) }
        factory { SaveTokenUseCaseSync(get()) }
    }
}