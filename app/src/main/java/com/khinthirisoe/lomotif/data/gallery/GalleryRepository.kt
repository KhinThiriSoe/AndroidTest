package com.khinthirisoe.lomotif.data.gallery

import com.khinthirisoe.lomotif.data.DataSourceProperties
import io.reactivex.Single

interface GalleryRepository {
    fun fetchImage(page: Int): Single<Image>
}

class GalleryRepositoryImpl(private val galleryDataSource: GalleryDataSource) :
    GalleryRepository {
    override fun fetchImage(page: Int): Single<Image> {
        return galleryDataSource.getImage(DataSourceProperties.VALUE_API_KEY, page)
    }
}

