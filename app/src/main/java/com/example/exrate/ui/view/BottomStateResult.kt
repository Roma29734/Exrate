package com.example.exrate.ui.view

sealed class BottomStateResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : BottomStateResult<T>(data)
    class Error<T>(message: String, data: T? = null) : BottomStateResult<T>(data, message)
    class Loading<T>(data: T? = null) : BottomStateResult<T>(data)
}