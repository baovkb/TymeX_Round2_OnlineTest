package com.vkbao.currencyconverter.data.repositories

import com.vkbao.currencyconverter.data.models.SuccessResponse
import com.vkbao.currencyconverter.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRateRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getExchangeRates(
        baseCurrency: String,
        currencies: String?
    ): Response<SuccessResponse<Double>> {
        return withContext(Dispatchers.IO) {
            apiService.getExchangeRates(baseCurrency, currencies)
        }
    }
}