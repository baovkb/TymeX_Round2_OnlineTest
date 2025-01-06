package com.vkbao.currencyconverter.core.utils

import com.vkbao.currencyconverter.data.models.SuccessResponse
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.SocketTimeoutException

object ApiResultHandler {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<SuccessResponse<T>>
    ) = flow {
        emit(ApiResult.Loading)
         try {
             val response = apiCall()
             if (response.isSuccessful) {
                 val body = response.body()!!
                 emit(ApiResult.Success(body))
             } else {
                 val errorMsg = response.errorBody()?.string() ?: "Unexpected Error"
                 emit(ApiResult.Error(errorMsg))
             }
        } catch (e: SocketTimeoutException) {
            emit(ApiResult.Error("Please check your internet connection"))
        } catch (e: Exception) {
            e.printStackTrace()
            val errorMsg = e.message ?: "Unexpected Error"
            emit(ApiResult.Error(errorMsg))
        }
    }
}