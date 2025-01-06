package com.vkbao.currencyconverter.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkbao.currencyconverter.core.utils.ApiResult
import com.vkbao.currencyconverter.core.utils.ApiResultHandler
import com.vkbao.currencyconverter.data.repositories.ExchangeRateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) : ViewModel() {
    private val _exchangeRates = MutableLiveData<ApiResult<Double>>()
    val exchangeRates: LiveData<ApiResult<Double>> = _exchangeRates

    fun getExchangeRates(baseCurrency: String) {
        viewModelScope.launch {
             ApiResultHandler.safeApiCall {
                exchangeRateRepository.getExchangeRates(baseCurrency, null)
            }.collect {state -> _exchangeRates.value = state}
        }
    }
}