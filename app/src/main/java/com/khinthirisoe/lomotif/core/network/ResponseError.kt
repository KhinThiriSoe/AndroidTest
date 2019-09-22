package com.khinthirisoe.lomotif.core.network

sealed class ResponseError(message: String?) : Throwable(message) {
    class NoInternetConnection(message: String?) : ResponseError(message)
}