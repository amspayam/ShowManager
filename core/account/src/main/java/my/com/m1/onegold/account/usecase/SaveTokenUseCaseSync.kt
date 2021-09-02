package my.com.m1.onegold.account.usecase

import com.combyne.core.usecase.AsyncSuspendUseCase
import my.com.m1.onegold.account.model.AccountHandler
import my.com.m1.onegold.account.model.TokenModel


class SaveTokenUseCaseSync constructor(
    private val accountHandler: AccountHandler
) : AsyncSuspendUseCase<TokenModel, Boolean> {

    override suspend fun executeAsync(rq: TokenModel): Boolean {
        return accountHandler.save(rq)
    }

}