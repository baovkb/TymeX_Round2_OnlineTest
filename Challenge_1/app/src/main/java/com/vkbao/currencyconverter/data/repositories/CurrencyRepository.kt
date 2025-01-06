package com.vkbao.currencyconverter.data.repositories

import com.vkbao.currencyconverter.data.models.Currency
import com.vkbao.currencyconverter.data.models.SuccessResponse
import com.vkbao.currencyconverter.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCurrencies(): Response<SuccessResponse<Currency>> {
        return withContext(Dispatchers.IO) {
            apiService.getCurrencies()
        }
    }
}