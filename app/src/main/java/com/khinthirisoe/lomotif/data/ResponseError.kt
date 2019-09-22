package com.khinthirisoe.lomotif.data

sealed class ResponseError(message: String?) : Throwable(message) {
    class NoInternetConnection(message: String?) : ResponseError(message)
}