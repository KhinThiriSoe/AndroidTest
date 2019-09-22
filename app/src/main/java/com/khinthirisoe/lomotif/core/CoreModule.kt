package com.khinthirisoe.lomotif.core

import com.khinthirisoe.lomotif.core.network.ApplicationNetworkUtils
import com.khinthirisoe.lomotif.core.network.NetworkUtils
import com.khinthirisoe.lomotif.core.rx.ApplicationSchedulerProvider
import com.khinthirisoe.lomotif.core.rx.SchedulerProvider
import org.koin.dsl.module

val coreModule = module {
    single { ApplicationNetworkUtils(get()) as NetworkUtils }
    single { ApplicationSchedulerProvider() as SchedulerProvider }
}