package com.khinthirisoe.lomotif.ui.gallery.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khinthirisoe.lomotif.core.ext.initSchedulers
import com.khinthirisoe.lomotif.core.mvvm.RxViewModel
import com.khinthirisoe.lomotif.core.network.NetworkUtils
import com.khinthirisoe.lomotif.data.gallery.GalleryRepository
import com.khinthirisoe.lomotif.data.gallery.Image
import com.khinthirisoe.lomotif.ui.Failed
import com.khinthirisoe.lomotif.ui.Loading
import com.khinthirisoe.lomotif.ui.ViewModelState

class GalleryViewModel(
    private val networkUtils: NetworkUtils,
    private val galleryRepository: GalleryRepository
) : RxViewModel(){

    private val _states = MutableLiveData<ViewModelState>()
    val states: LiveData<ViewModelState>
        get() = _states

    fun fetchData(key: String, page: Int) {
        _states.value = Loading
        launch {
            networkUtils.isInternetOn()
                .andThen(
                    galleryRepository.fetchImage(key, page).initSchedulers()
                ).subscribe({ image ->
                    _states.value =
                        GalleryLoaded(
                            image
                        )
                }, { error ->
                    _states.value = Failed(error)
                })
        }
    }

    data class GalleryLoaded(val gallery: Image) : ViewModelState()

}