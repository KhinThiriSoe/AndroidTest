package com.khinthirisoe.lomotif.core.network

import io.reactivex.Completable

interface NetworkUtils {
    fun isInternetOn(): Completable
}