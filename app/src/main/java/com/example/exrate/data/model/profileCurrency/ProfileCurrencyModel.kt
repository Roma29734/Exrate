package com.example.exrate.data.model.profileCurrency

data class ProfileCurrencyModel(
    val code: Int,
    val info: Info,
    val msg: String,
    val response: List<Response>,
    val status: Boolean
)