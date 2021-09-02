package my.com.m1.repository.network.entity.base

import androidx.annotation.Keep

@Keep
data class BaseErrorApiEntity(
    var errorCode: Int? = null,
    var message: String? = null,
    var details: Any? = null
)