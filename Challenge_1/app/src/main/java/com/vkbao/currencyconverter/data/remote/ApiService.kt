package com.vkbao.currencyconverter.data.remote

import com.vkbao.currencyconverter.data.models.Currency
import com.vkbao.currencyconverter.data.models.SuccessResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("currencies")
    suspend fun getCurrencies(): Response<SuccessResponse<Currency>>

    @GET("latest")
    suspend fun getExchangeRates(
        @Query("base_currency") baseCurrency: String,
        @Query("currencies") currencies: String?
    ) : Response<SuccessResponse<Double>>
}