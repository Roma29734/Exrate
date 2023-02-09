package com.example.exrate.data.model.listSupported

data class ListSupportedModel(
    val code: Int,
    val info: Info,
    val msg: String,
    val response: List<Response>,
    val status: Boolean
)