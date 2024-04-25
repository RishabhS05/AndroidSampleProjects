package com.example.baseArchitecture.dataLayer.state

sealed class RequestState<out R> {
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val exception: Exception, val errorCode: Int = 0) : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
}