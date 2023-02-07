package com.example.exrate.data.model.latest

data class LatestModel(
    val code: Int,
    val info: Info,
    val msg: String,
    val response: List<Response>,
    val status: Boolean
)