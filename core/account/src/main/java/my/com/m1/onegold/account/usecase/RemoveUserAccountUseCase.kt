package my.com.m1.onegold.account.usecase

import com.combyne.core.usecase.AsyncSuspendUseCase
import my.com.m1.onegold.account.model.AccountHandler

class RemoveUserAccountUseCase constructor(
    private val accountHandler: AccountHandler
) : AsyncSuspendUseCase<Unit, Boolean> {

    override suspend fun executeAsync(rq: Unit): Boolean {
        return accountHandler.remove()
    }

}