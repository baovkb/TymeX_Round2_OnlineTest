package com.vkbao.currencyconverter.core.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("apikey", apiKey)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}