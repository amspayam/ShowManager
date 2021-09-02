package my.com.m1.onegold.account.usecase

import com.combyne.core.usecase.SyncUseCase
import my.com.m1.onegold.account.model.AccountHandler

class GetTokenUseCaseSync constructor(
        private val accountHandler: AccountHandler
) : SyncUseCase<Unit, String> {

    override fun executeSync(rq: Unit): String {
        return accountHandler.authToken()
    }

}