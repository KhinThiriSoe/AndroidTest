package com.khinthirisoe.lomotif.di

import com.khinthirisoe.lomotif.core.coreModule
import com.khinthirisoe.lomotif.data.gallery.GalleryRepository
import com.khinthirisoe.lomotif.data.gallery.GalleryRepositoryImpl
import com.khinthirisoe.lomotif.data.remoteDataSourceModule
import com.khinthirisoe.lomotif.ui.gallery.GalleryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<GalleryRepository> {
        GalleryRepositoryImpl(
            get()
        )
    }
    viewModel { GalleryViewModel(get(), get(), get()) }

}

val app = listOf(appModule, coreModule, remoteDataSourceModule)