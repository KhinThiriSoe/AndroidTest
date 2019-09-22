package com.khinthirisoe.lomotif.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khinthirisoe.lomotif.core.ext.with
import com.khinthirisoe.lomotif.core.mvvm.RxViewModel
import com.khinthirisoe.lomotif.core.network.NetworkUtils
import com.khinthirisoe.lomotif.core.rx.SchedulerProvider
import com.khinthirisoe.lomotif.data.gallery.GalleryRepository
import com.khinthirisoe.lomotif.data.gallery.Image
import com.khinthirisoe.lomotif.ui.Failed
import com.khinthirisoe.lomotif.ui.Loading
import com.khinthirisoe.lomotif.ui.ViewModelState

class GalleryViewModel(
    private val networkUtils: NetworkUtils,
    private val galleryRepository: GalleryRepository,
    private val schedulerProvider: SchedulerProvider
    ) : RxViewModel(){

    private val _states = MutableLiveData<ViewModelState>()
    val states: LiveData<ViewModelState>
        get() = _states

    fun fetchData(key: String, page: Int) {
        _states.value = Loading
        launch {
            networkUtils.isInternetOn()
                .andThen(galleryRepository.fetchImage(key, page))
                .with(schedulerProvider)
                .subscribe(
                { image ->
                    _states.value = GalleryLoaded(image)
                },
                { error ->
                    _states.value = Failed(error)
                })
        }
    }

    data class GalleryLoaded(val gallery: Image) : ViewModelState()

}