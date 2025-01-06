package com.vkbao.currencyconverter.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<out T>(
    val data: Map<String, T>
)