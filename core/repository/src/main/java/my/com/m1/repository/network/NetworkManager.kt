package my.com.m1.repository.network

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import com.combyne.cachemanager.cache.CacheManager
import com.github.simonpercic.oklog3.OkLogInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import my.com.m1.onegold.account.usecase.GetTokenUseCaseSync
import my.com.m1.repository.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class NetworkManager(
    private val context: Context,
    private val cacheManager: CacheManager,
    private val getTokenUseCaseSync: GetTokenUseCaseSync,
    private val networkCallback: NetworkCallbackX
) {

    fun <T> createRetrofit(serviceClass: Class<T>): T = getClient().create(serviceClass)
    fun createApollo() = getClientApollo()

    private fun getClientApollo(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder()
            .headerInterceptor(networkCallback)
            .apply {
                if (BuildConfig.DEBUG) {
                    logInterceptor()
                    okLogInterceptor()
                    chuckInterceptor()
                }
            }
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()
        return ApolloClient.builder()
            .serverUrl(BuildConfig.BASE_URL)
            .subscriptionTransportFactory(
                WebSocketSubscriptionTransport.Factory(
                    BuildConfig.BASE_URL,
                    okHttpClient
                )
            )
            .okHttpClient(okHttpClient)
            .build()
    }

    private fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder()
                .headerInterceptor(networkCallback)
                .apply {
                    if (BuildConfig.DEBUG) {
                        logInterceptor()
                        okLogInterceptor()
                        chuckInterceptor()
                    }
                }
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build())
            .build()
    }

    private fun getDeviceId(): String {
        var deviceId: String =
            cacheManager.get(CacheManager.DEVICE_ID, "") ?: ""
        return if (deviceId.isNotEmpty()) {
            deviceId
        } else {
            deviceId = UUID.randomUUID().toString()
            cacheManager.put(CacheManager.DEVICE_ID, deviceId)
            deviceId
        }
    }

    private fun isDeviceSign(): Boolean {
        return cacheManager.get(CacheManager.DEVICE_SIGN_STATE, false)
    }

    private fun OkHttpClient.Builder.headerInterceptor(networkCallback: NetworkCallbackX): OkHttpClient.Builder {
        return this.addInterceptor { chain ->
            val res: okhttp3.Response
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder
                .method(original.method(), original.body())

            with(getTokenUseCaseSync.executeSync(Unit)) {
                if (isEmpty()) return@with
                requestBuilder.header("Authorization", this)
            }

            requestBuilder.header(
                "X-Parse-Application-Id",
                "AaQjHwTIQtkCOhtjJaN/nDtMdiftbzMWW5N8uRZ+DNX9LI8AOziS10eHuryBEcCI"
            )
            requestBuilder.header(
                "X-Parse-Master-Key",
                "yiCk1DW6WHWG58wjj3C4pB/WyhpokCeDeSQEXA5HaicgGh4pTUd+3/rMOR5xu1Yi"
            )

            val request = requestBuilder.build()
            res = chain.proceed(request)
            if (res.code() == 401)
                networkCallback.unAuthorize()
            res
        }
    }

    private fun OkHttpClient.Builder.chuckInterceptor(): OkHttpClient.Builder {
        return this.addInterceptor(ChuckInterceptor(context))
    }

    private fun OkHttpClient.Builder.logInterceptor(): OkHttpClient.Builder {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return this.addInterceptor(interceptor)
    }


    private fun OkHttpClient.Builder.okLogInterceptor(): OkHttpClient.Builder {
        val okLogInterceptor = OkLogInterceptor.builder().build();
        return this.addInterceptor(okLogInterceptor)
    }

    companion object {
        private const val AB_CHANNEL_HEADER = "MOBILE-APP,AndroidDevice"
    }
}

interface NetworkCallbackX {
    fun unAuthorize()
}