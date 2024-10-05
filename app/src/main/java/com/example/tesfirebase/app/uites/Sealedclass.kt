package com.example.tesfirebase.app.uites

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val errorMessage: Any) : DataState<Nothing>()



}