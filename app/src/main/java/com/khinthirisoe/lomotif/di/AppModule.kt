package com.khinthirisoe.lomotif.di

import com.khinthirisoe.lomotif.base.ApplicationSchedulerProvider
import com.khinthirisoe.lomotif.base.SchedulerProvider
import com.khinthirisoe.lomotif.data.ApplicationNetworkUtils
import com.khinthirisoe.lomotif.data.NetworkUtils
import org.koin.dsl.module

val appModule = module {

    single { ApplicationNetworkUtils(get()) as NetworkUtils }

}

val rxModule = module {
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}

val app = listOf(appModule, rxModule)