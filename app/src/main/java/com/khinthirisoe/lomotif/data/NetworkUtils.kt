package com.khinthirisoe.lomotif.data

import io.reactivex.Completable

interface NetworkUtils {
    fun isInternetOn(): Completable
}