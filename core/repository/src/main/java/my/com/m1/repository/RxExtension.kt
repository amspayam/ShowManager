package my.com.m1.repository

import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import my.com.m1.repository.network.entity.RestErrorResponse
import retrofit2.HttpException
import javax.net.ssl.HttpsURLConnection

fun handleApiError(error: Throwable): String {
    return if (error is HttpException) {
        when (error.code()) {
            HttpsURLConnection.HTTP_UNAUTHORIZED -> ("Unauthorised User ")
            HttpsURLConnection.HTTP_FORBIDDEN -> ("Forbidden")
            HttpsURLConnection.HTTP_INTERNAL_ERROR -> ("Internal Server Error")
            HttpsURLConnection.HTTP_BAD_REQUEST -> ("Bad Request")
            else -> (error.localizedMessage.orEmpty())
        }
    } else {
        ""
    }
}

fun Completable.executeUseCase(
    success: (() -> Unit) = {},
    error: (RestErrorResponse) -> Unit
): Disposable {
    return this.subscribe({ success.invoke() },
        { error.invoke(fromNetworkThrowable(it)) })
}

fun <R> Single<R>.executeUseCase(
    success: ((R) -> Unit) = {},
    error: (RestErrorResponse) -> Unit
): Disposable {
    return this.subscribe({ success.invoke(it) },
        { error.invoke(fromNetworkThrowable(it)) })
}

fun <R> Observable<R>.executeUseCase(
    success: ((R) -> Unit) = {},
    error: (RestErrorResponse) -> Unit
): Disposable {
    return this.subscribe({ success.invoke(it) },
        { error.invoke(fromNetworkThrowable(it)) })
}

/**
 * From network throwable data layer error.
 *
 * @param throwable the throwable
 * @return the data layer error
 */
fun fromNetworkThrowable(throwable: Throwable): RestErrorResponse {
    throwable.printStackTrace()
    val defaultMessage = throwable.message ?: "Error : ${throwable.printStackTrace()}"
    return if (throwable is HttpException) {
        val networkError = throwable.response()
        try {
            val errorModel = networkError?.errorBody()?.let {
                Gson().fromJson(
                    it.string().toString(),
                    RestErrorResponse::class.java
                )
            } ?: RestErrorResponse(-1, defaultMessage)
            errorModel
        } catch (e: Exception) {
            RestErrorResponse(-1, defaultMessage)
        }
    } else {
        RestErrorResponse(-1, defaultMessage)
    }
}


fun Int.forbiddenStatus(): Boolean {
    return this == 403
}

fun Int.notFoundStatus(): Boolean {
    return this == 404
}

fun Int.conflictStatus(): Boolean {
    return this == 409
}

fun Int.internalServerStatus(): Boolean {
    return this == 500
}

fun Int.unAuthorizeStatus(): Boolean {
    return this == 401
}

fun Int.invalidInputStatus(): Boolean {
    return this == 400
}
