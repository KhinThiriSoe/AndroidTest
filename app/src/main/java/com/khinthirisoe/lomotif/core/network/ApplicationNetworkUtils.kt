package com.khinthirisoe.lomotif.core.network

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Completable

class ApplicationNetworkUtils(
    private val context: Context
) : NetworkUtils {

    /**
     * Made completable to be able to be added into Rx Stream
     * */
    override fun isInternetOn(): Completable {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return if (activeNetworkInfo != null && activeNetworkInfo.isConnected)
            Completable.complete()
        else
            Completable.error(
                ResponseError.NoInternetConnection(
                    "No internet connection"
                )
            )
    }

}