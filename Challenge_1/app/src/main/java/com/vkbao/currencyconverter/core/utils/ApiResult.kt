package com.vkbao.currencyconverter.core.utils

import com.vkbao.currencyconverter.data.models.SuccessResponse

sealed class ApiResult<out T> {
    object Loading: ApiResult<Nothing>()
    class Success<out T>(val data: SuccessResponse<T>): ApiResult<T>()
    class Error(val message: String): ApiResult<Nothing>()
}