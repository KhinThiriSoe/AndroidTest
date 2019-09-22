package com.khinthirisoe.lomotif.data.gallery

import io.reactivex.Single

interface GalleryRepository {
    fun fetchImage(key: String, page: Int): Single<List<Image>>
}

class GalleryRepositoryImpl(private val galleryDataSource: GalleryDataSource) :
    GalleryRepository {
    override fun fetchImage(key: String, page: Int): Single<List<Image>> {
        return galleryDataSource.getImage(key, page)
    }
}

