package my.com.m1.onegold.account

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Bundle
import my.com.m1.onegold.account.model.AccountConstant
import my.com.m1.onegold.account.model.AccountHandler
import my.com.m1.onegold.account.model.TokenModel

class AccountHandlerImpl constructor(
    private val accountManager: AccountManager
) : AccountHandler {

    override fun save(token: TokenModel): Boolean {
        val account = Account(token.username, AccountConstant.ACCOUNT_TYPE)
        val extraData = Bundle()
        extraData.putString(AccountConstant.PASSWORD, token.password)

        // Creating the account on the device and setting the auth token we got
        // (Note setting the auth token will cause another call to the server to authenticate the user)
        val resultCreatedAccount =
            accountManager.addAccountExplicitly(account, token.password, extraData)

        return if (resultCreatedAccount) {
            accountManager.setAuthToken(
                account,
                AccountConstant.AUTHTOKEN_TYPE_FULL_ACCESS,
                token.accessToken
            )
            true
        } else {
            false
        }
    }

    override fun remove(): Boolean {

        val accounts = accountManager.getAccountsByType(AccountConstant.ACCOUNT_TYPE)

        return accounts.getOrNull(0)?.name?.let { accountName ->
            val account = Account(accountName, AccountConstant.ACCOUNT_TYPE)
            return accountManager.removeAccountExplicitly(account)
        } ?: run {
            true
        }

    }

    override fun authToken(): String {
        val accounts = accountManager.getAccountsByType(AccountConstant.ACCOUNT_TYPE)
        if (accounts.isEmpty()) return ""

        return accountManager.peekAuthToken(accounts[0], AccountConstant.AUTHTOKEN_TYPE_FULL_ACCESS)
    }


} 