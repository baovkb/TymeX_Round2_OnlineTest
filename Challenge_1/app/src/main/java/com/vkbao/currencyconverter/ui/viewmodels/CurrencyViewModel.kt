package com.vkbao.currencyconverter.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkbao.currencyconverter.core.utils.ApiResult
import com.vkbao.currencyconverter.core.utils.ApiResultHandler
import com.vkbao.currencyconverter.data.models.Currency
import com.vkbao.currencyconverter.data.repositories.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    private val _currencies = MutableLiveData<ApiResult<Currency>>()
    val currencies: LiveData<ApiResult<Currency>> = _currencies

    fun getCurrencies() {
        viewModelScope.launch {
            ApiResultHandler.safeApiCall {
                currencyRepository.getCurrencies()
            }.collect { state -> _currencies.value = state }
        }
    }
}