package my.com.m1.repository.network

import com.combyne.cachemanager.cache.CacheManager
import my.com.m1.onegold.account.usecase.RemoveUserAccountUseCase
import my.com.m1.onegold.shared.viewmodel.ApplicationShareViewModel

open class NetworkCallbackImpl(
    private val cacheManager: CacheManager,
    private val applicationShareViewModel: ApplicationShareViewModel,
    private val removeUserAccountUseCase: RemoveUserAccountUseCase
) : NetworkCallbackX {

    var unAuthorizeCallBack: (() -> Unit)? = null

    override fun unAuthorize(){

//        removeUserAccountUseCase.executeAsync(Unit)
//        // Remove from cache
////        cacheManager.remove(ProfileConstant.CashManagerKey.USER)
//
//        // Remove from shareViewModel
//        applicationShareViewModel.user.postValue(null)

        unAuthorizeCallBack?.invoke()
//        return@runBlocking
    }


}