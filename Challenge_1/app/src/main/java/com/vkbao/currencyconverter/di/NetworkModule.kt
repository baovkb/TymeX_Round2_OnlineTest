package com.vkbao.currencyconverter.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vkbao.currencyconverter.BuildConfig
import com.vkbao.currencyconverter.core.utils.AuthInterceptor
import com.vkbao.currencyconverter.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val API_KEY = BuildConfig.API_KEY

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }
        val mediaType = "application/json; charset=UTF8".toMediaType()

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(API_KEY))
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.freecurrencyapi.com/v1/")
            .addConverterFactory(json.asConverterFactory(mediaType))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providerApiService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)
}