package my.com.m1.onegold.account.model

interface AccountHandler {
    fun save(token: TokenModel): Boolean
    fun remove(): Boolean
    fun authToken(): String
}