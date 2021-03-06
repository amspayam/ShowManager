package com.combyne.repository.network

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import com.combyne.cache.CacheManager
import com.github.simonpercic.oklog3.OkLogInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import my.com.m1.repository.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class NetworkManager(
    private val context: Context,
    private val cacheManager: CacheManager
) {

    fun createApollo() = getClientApollo()

    private fun getClientApollo(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder()
            .headerInterceptor()
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

    private fun OkHttpClient.Builder.headerInterceptor(): OkHttpClient.Builder {
        return this.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder
                .method(original.method(), original.body())

            requestBuilder.header(
                "X-Parse-Application-Id",
                "AaQjHwTIQtkCOhtjJaN/nDtMdiftbzMWW5N8uRZ+DNX9LI8AOziS10eHuryBEcCI"
            )
            requestBuilder.header(
                "X-Parse-Master-Key",
                "yiCk1DW6WHWG58wjj3C4pB/WyhpokCeDeSQEXA5HaicgGh4pTUd+3/rMOR5xu1Yi"
            )

            val request = requestBuilder.build()
            chain.proceed(request)
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
        val okLogInterceptor = OkLogInterceptor.builder().build()
        return this.addInterceptor(okLogInterceptor)
    }

}